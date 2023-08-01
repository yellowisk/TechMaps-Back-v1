package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.gateway.StepDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StepDAOImpl implements StepDAO {
    private final JdbcTemplate jdbcTemplate;

    public StepDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean StepExists(UUID stepId) {
        return null;
    }

    @Override
    public Step saveStep(Step step) {
        return null;
    }

    @Override
    public Optional<Step> findStepById(UUID stepId) {
        return Optional.empty();
    }

    @Override
    public List<Step> findStepsByTaskId(UUID taskId) {
        return null;
    }

    @Override
    public Step prioritizeStep(Step step) {
        return null;
    }

    @Override
    public Step finishStep(Step step) {
        return null;
    }

    @Override
    public Step deleteStepById(UUID stepId) {
        return null;
    }
}
