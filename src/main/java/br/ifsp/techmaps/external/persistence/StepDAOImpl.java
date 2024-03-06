package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.gateway.StepDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class StepDAOImpl implements StepDAO {
    private final JdbcTemplate jdbcTemplate;

    public StepDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.step-dao.insert.step}")
    private String insertStepQuery;
    @Value("${queries.sql.step-dao.select.step-by-id}")
    private String selectStepByIdQuery;
    @Value("${queries.sql.step-dao.select.step-by-task-id}")
    private String selectStepByTaskIdQuery;
    @Value("${queries.sql.step-dao.update.step-is-priority}")
    private String updateStepPrioritizeQuery;
    @Value("${queries.sql.step-dao.update.step-is-finished}")
    private String updateStepFinishQuery;
    @Value("${queries.sql.step-dao.update.step-number}")
    private String updateStepNumberQuery;
    @Value("${queries.sql.step-dao.delete.step-by-id}")
    private String deleteStepByIdQuery;
    @Value("${queries.sql.step-dao.exists.step-by-id}")
    private String existsStepByIdQuery;

    @Override
    public Step saveStep(Step step) {

        jdbcTemplate.update(insertStepQuery, step.getId(), step.getTaskId(), step.getNumber(),
                step.getText(), false, false);

        return Step.createFull(step.getId(), step.getTaskId(), step.getNumber(),
                step.getText(), step.getLink(), false, false);
    }

    @Override
    public Optional<Step> findStepById(UUID stepId) {
        Step step = jdbcTemplate.queryForObject(selectStepByIdQuery,
                this::mapperFromRs, stepId);

        if(Objects.isNull(step)) {
            throw new IllegalStateException("Step not found");
        }

        return Optional.of(step);
    }

    @Override
    public List<Step> findStepsByTaskId(UUID taskId) {
        return jdbcTemplate.query(selectStepByTaskIdQuery, this::mapperFromRs, taskId);
    }

    @Override
    public Step prioritizeStep(Step step) {
        jdbcTemplate.update(updateStepPrioritizeQuery, step.isPriority(), step.getId());
        return step;
    }

    @Override
    public Step finishStep(Step step) {
        jdbcTemplate.update(updateStepFinishQuery, step.isFinished(), step.getId());
        return step;
    }

    @Override
    public Step deleteStepById(UUID stepId) {
        Optional<Step> step = findStepById(stepId);

        if(step.isEmpty()) {
            throw new IllegalStateException("Couldn't find step with id: " + stepId);
        }

        jdbcTemplate.update(deleteStepByIdQuery, stepId);
        return step.get();
    }

    @Override
    public Boolean StepExists(UUID stepId) {
        return jdbcTemplate.queryForObject(existsStepByIdQuery, Boolean.class, stepId);
    }

    public Step mapperFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID stepId = (UUID) rs.getObject("id");
        UUID taskId = (UUID) rs.getObject("task_id");
        int number = rs.getInt("position");
        String text = rs.getString("text");
        String link = rs.getString("link");
        Boolean isFinished = rs.getBoolean("is_finished");
        Boolean isPriority = rs.getBoolean("is_priority");
        return Step.createFull(stepId, taskId, number, text, link, isFinished, isPriority);
    }

}
