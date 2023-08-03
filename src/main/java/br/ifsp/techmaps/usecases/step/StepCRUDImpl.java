package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.usecases.step.gateway.StepDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;

import java.util.List;
import java.util.UUID;

public class StepCRUDImpl implements StepCRUD {

    private final StepDAO stepDAO;
    private final TaskDAO taskDAO;

    public StepCRUDImpl(StepDAO stepDAO, TaskDAO taskDAO) {
        this.stepDAO = stepDAO;
        this.taskDAO = taskDAO;
    }

    @Override
    public Step addNewTaskSteps(UUID taskId) {
        return null;
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
