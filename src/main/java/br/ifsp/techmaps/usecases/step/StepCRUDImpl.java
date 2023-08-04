package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.gateway.StepDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.step.request.CreateStepRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StepCRUDImpl implements StepCRUD {

    private final StepDAO stepDAO;
    private final TaskDAO taskDAO;

    public StepCRUDImpl(StepDAO stepDAO, TaskDAO taskDAO) {
        this.stepDAO = stepDAO;
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Step> generateTaskSteps(UUID taskId) {
        return null;
    }

    @Override
    public Step addTask(UUID taskId, CreateStepRequest request) {
        Step step = request.convertToStep(taskId);
        return stepDAO.saveStep(step);
    }

    @Override
    public List<Step> getStepsByTaskId(UUID taskId) {
        return null;
    }

    @Override
    public Step updateStepNumber(Step step) {
        return null;
    }

    @Override
    public Step prioritizeTaskStep(Step step) {
        return null;
    }

    @Override
    public Step finishTaskStep(Step step) {
        return null;
    }

    @Override
    public Step deleteTaskStep(UUID stepId) {
        return null;
    }
}
