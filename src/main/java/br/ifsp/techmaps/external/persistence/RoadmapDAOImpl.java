package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.*;
import java.util.*;

@Repository
public class RoadmapDAOImpl implements RoadmapDAO {

    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;

    @Value("${queries.sql.roadmap-dao.exists.roadmap-by-id}")
    private String existsRoadmapQuery;

    @Value("${queries.sql.roadmap-dao.insert.roadmap}")
    private String insertRoadmapQuery;

    @Value("${queries.sql.roadmap-dao.select.roadmap-by-id}")
    private String selectRoadmapByIdQuery;

    @Value("${queries.sql.roadmap-dao.select.all-complete-roadmaps-by-dashboard-id}")
    private String selectAllCompleteRoadmapsByDashboardIdQuery;

    @Value("${queries.sql.roadmap-dao.select.all-roadmaps-by-dashboard-id}")
    private String selectAllRoadmapsByDashboardIdQuery;

    @Value("${queries.sql.roadmap-dao.update.complete-roadmap}")
    private String updateRoadmapStatusAndCommitCounterQuery;

    public RoadmapDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public Boolean RoadmapExists(UUID roadmapId) {
        return jdbcTemplate.queryForObject(existsRoadmapQuery, Boolean.class, roadmapId);
    }

    @Override
    public Roadmap saveRoadmap(Roadmap roadmap) {

        UUID roadmapId = UUID.randomUUID();

        jdbcTemplate.update(insertRoadmapQuery, roadmapId, roadmap.getTitle(), roadmap.getType().name(),
                roadmap.getRoadmapStatus().name(), roadmap.getRoadmapLanguage().name(),
                roadmap.getStartTime(), roadmap.getFinishTime(), roadmap.getTotalTime(),
                roadmap.getRoadmapCommits(), roadmap.getDashboardId());

        return Roadmap.createWithoutStages(roadmapId, roadmap.getTitle(), roadmap.getType(),
                roadmap.getRoadmapStatus(), roadmap.getRoadmapLanguage(), roadmap.getStartTime(),
                roadmap.getFinishTime(), roadmap.getTotalTime(), roadmap.getRoadmapCommits(),
                roadmap.getDashboardId());
    }

    @Override
    public Optional<Roadmap> findRoadmapById(UUID roadmapId) {
        try {
            Roadmap roadmap = jdbcTemplate.queryForObject(selectRoadmapByIdQuery,
                    this::mapperRoadmapFromRs, roadmapId);

            if (Objects.isNull(roadmap)) {
                throw new IllegalStateException();
            }

            return Optional.of(roadmap);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Roadmap> findAllCompletedByDashboardId(UUID dashboardId) {
        return jdbcTemplate.query(selectAllCompleteRoadmapsByDashboardIdQuery, this::mapperRoadmapFromRs, dashboardId);
    }

    @Override
    public List<Roadmap> findAllByDashboardId(UUID dashboardId) {
        return jdbcTemplate.query(selectAllRoadmapsByDashboardIdQuery, this::mapperRoadmapFromRs, dashboardId);
    }

    @Override
    public Roadmap updateRoadmap(Roadmap roadmap) {

        Timestamp startTime = roadmap.getStartTime();
        Timestamp finishTime = Timestamp.valueOf(LocalDateTime.now());
        Long totalTime = (finishTime.getTime() - startTime.getTime())/1000;

        jdbcTemplate.update(updateRoadmapStatusAndCommitCounterQuery, roadmap.getRoadmapStatus().name(),
                roadmap.getRoadmapCommits(), finishTime, totalTime, roadmap.getRoadmapId());
        return roadmap;
    }

    public Roadmap mapperRoadmapFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String description = rs.getString("title");
        RoadmapType type = RoadmapType.valueOf(rs.getString("type"));
        RoadmapStatus status = RoadmapStatus.valueOf(rs.getString("status"));
        RoadmapLanguage language = RoadmapLanguage.valueOf(rs.getString("lang"));
        Timestamp startTime = rs.getTimestamp("start_time");
        Timestamp finishTime = rs.getTimestamp("finish_time");
        Long total_time = rs.getLong("total_time");
        int commit_counter = rs.getInt("commit_counter");
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        return Roadmap.createWithoutStages(id, description, type, status, language,
                startTime, finishTime, total_time, commit_counter, dashboardId);
    }

}
