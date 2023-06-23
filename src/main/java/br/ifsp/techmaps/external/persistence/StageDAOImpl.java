package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StageDAOImpl implements StageDAO {

    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;

    @Value("${queries.sql.stage-dao.insert.stage}")
    private String insertStageQuery;

    public StageDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public Boolean StageExists(UUID stageId) {
        return null;
    }

    @Override
    public Stage saveStage(Stage stage) {
        UUID stageId = UUID.randomUUID();

        System.out.println(stageId);
        System.out.println(stage.getRoadmap());
        System.out.println(stage.getRoadmap().getRoadmapId());
        System.out.println(stage.getTheme().name());
        System.out.println(stage.getStageStatus().name());
        System.out.println(stage.getStageCommit());

        jdbcTemplate.update(insertStageQuery, stageId,
                stage.getRoadmap().getRoadmapId(), stage.getTheme().name(),
                stage.getStageStatus().name(), stage.getStageCommit());

        return Stage.createStageWithOnlyId(stageId);
    }

    @Override
    public Stage getStageById(UUID stageId) {
        return null;
    }

    @Override
    public List<Stage> getStagesByRoadmapId(UUID roadmapId) {
        return null;
    }
}
