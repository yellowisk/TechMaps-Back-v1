package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class StageDAOImpl implements StageDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RoadmapDAO roadmapDAO;

    @Value("${queries.sql.stage-dao.insert.stage}")
    private String insertStageQuery;
    @Value("${queries.sql.stage-dao.select.stage-by-id}")
    private String selectStageByIdQuery;
    @Value("${queries.sql.stage-dao.select.stage-by-roadmap-id}")
    private String selectStageByRoadmapIdQuery;
    @Value("${queries.sql.stage-dao.select.commit-state-by-stage-id}")
    private String selectCommitStateByStageIdQuery;
    @Value("${queries.sql.stage-dao.select.date-finished-of-tasks-by-stage-id}")
    private String selectDateFinishedOfTasksByStageIdQuery;
    @Value("${queries.sql.stage-dao.update.stage-commit-counter}")
    private String updateStageCommitCounterQuery;
    @Value("${queries.sql.stage-dao.update.stage-status}")
    private String updateStageStatusQuery;
    @Value("${queries.sql.stage-dao.delete.stage-by-id}")
    private String deleteStageByIdQuery;
    @Value("${queries.sql.stage-dao.delete.task-commit-by-stage-id}")
    private String deleteTaskCommitByStageIdQuery;
    @Value("${queries.sql.stage-dao.exists.stage-id}")
    private String existsStageIdQuery;

    public StageDAOImpl(JdbcTemplate jdbcTemplate,
                        RoadmapDAO roadmapDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.roadmapDAO = roadmapDAO;
    }

    @Override
    public Boolean StageExists(UUID stageId) {
        Boolean exists = jdbcTemplate.queryForObject(existsStageIdQuery, Boolean.class, stageId);
        return Objects.nonNull(exists) && exists;
    }

    @Override
    public Stage saveStage(Stage stage) {
        UUID stageId = stage.getStageId();

        if (stage.getRoadmap().getRoadmapStatus() == RoadmapStatus.COMPLETE) {
            throw new IllegalStateException("Roadmap is already done!");
        }

        jdbcTemplate.update(insertStageQuery, stageId,
                stage.getRoadmap().getRoadmapId(), stage.getTheme().name(),
                stage.getStageStatus().name(), stage.getStageCommit());

        return Stage.createStageWithoutTasks(stageId, stage.getRoadmap(), stage.getTheme(),
                stage.getStageStatus(), stage.getStageCommit());
    }

    @Override
    public Optional<Stage> findStageById(UUID stageId) {
        Stage stage;
        try {
            stage = jdbcTemplate.queryForObject(selectStageByIdQuery,
                    this::mapperStageFromRs, stageId);

            if(Objects.isNull(stage))
                throw new NullPointerException("Stage not found");

            return Optional.of(stage);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Stage> findStagesByRoadmapId(UUID roadmapId) {
        Optional<Roadmap> roadmapOptional = roadmapDAO.findRoadmapById(roadmapId);

        if (roadmapOptional.isEmpty()) {
            throw new IllegalStateException("Couldn't find roadmap with id: " + roadmapId);
        }

        List<Stage> stages = jdbcTemplate.query(selectStageByRoadmapIdQuery,
                this::mapperStageFromRs, roadmapId);

        return stages.stream().toList();
    }

    @Override
    public List<Timestamp> findDateFinishedOfTasksByStageId(UUID stageId) {
        return jdbcTemplate.query(selectDateFinishedOfTasksByStageIdQuery,
                (rs, rowNum) -> rs.getTimestamp("date_finished"), stageId);
    }

    @Override
    public List<CommitState> findCommitsByStageId(UUID stageId) {
        List<CommitState> commitStates = new ArrayList<>();

        List<String> commitStatesString = jdbcTemplate.query(selectCommitStateByStageIdQuery,
                (rs, rowNum) -> rs.getString("state"), stageId);

        for (String commitStateString : commitStatesString) {
            commitStates.add(CommitState.valueOf(commitStateString));
        }

        return commitStates;
    }

    @Override
    public Stage updateStage(Stage stage) {
        jdbcTemplate.update(updateStageCommitCounterQuery, ps -> {
            ps.setObject(1, stage.getStageCommit());
            ps.setObject(2, stage.getStageId());
        });
        return stage;
    }

    @Override
    public Stage updateStageStatus(Stage stage) {

        UUID roadmapId = stage.getRoadmap().getRoadmapId();
        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();
        if (roadmap.getRoadmapStatus() == RoadmapStatus.COMPLETE) {
            throw new IllegalStateException("Roadmap is already complete!");
        }

        jdbcTemplate.update(updateStageStatusQuery, ps -> {
            ps.setObject(1, stage.getStageStatus().name());
            ps.setObject(2, stage.getStageId());
        });

        Integer stageDoneCounter = 0;
        Integer commitStagedCounter = 0;
        List<Stage> stages = jdbcTemplate.query(selectStageByRoadmapIdQuery,
                this::mapperStageFromRs, roadmapId);

        if (stage.getStageStatus().equals(StageStatus.DONE)) {
            for (Stage roadmapStage : stages) {
                if (roadmapStage.getStageStatus().equals(StageStatus.DONE)) {
                    stageDoneCounter++;
                    commitStagedCounter = commitStagedCounter + roadmapStage.getStageCommit();
                }
            }
        }

        if (stageDoneCounter == stages.size()) {
            roadmap.setRoadmapStatus(RoadmapStatus.COMPLETE);
            roadmap.setRoadmapCommits(commitStagedCounter);
            roadmapDAO.updateRoadmapTime(roadmap);
        }

        return stage;
    }

    @Override
    public Stage deleteStageById(UUID stageId) {
        Optional<Stage> stageOptional = findStageById(stageId);

        if (stageOptional.isEmpty()) {
            throw new IllegalStateException("Couldn't find stage with id: " + stageId);
        }

        jdbcTemplate.update(deleteTaskCommitByStageIdQuery, stageId);
        jdbcTemplate.update(deleteStageByIdQuery, stageId);

        return stageOptional.get();
    }

    private Stage mapperStageFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID roadmapId = (UUID) rs.getObject("roadmap_id");
        StageEnum theme = StageEnum.valueOf(rs.getString("theme"));
        StageStatus status = StageStatus.valueOf(rs.getString("status"));
        Integer stageCommit = Integer.valueOf(rs.getString("commit_counter"));

        Roadmap rm = roadmapDAO.findRoadmapById(roadmapId).get();


        return Stage.createStageWithoutTasks(id, rm, theme, status, stageCommit);
    }

}
