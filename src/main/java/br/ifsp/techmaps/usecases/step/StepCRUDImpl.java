package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.domain.entities.step.StepDescription;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.usecases.step.gateway.StepDAO;
import br.ifsp.techmaps.usecases.step.gateway.StepDescriptionDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.step.request.CreateStepRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StepCRUDImpl implements StepCRUD {

    private final StepDAO stepDAO;
    private final StepDescriptionDAO stepDescriptionDAO;
    private final TaskDAO taskDAO;

    public StepCRUDImpl(StepDAO stepDAO, StepDescriptionDAO stepDescriptionDAO, TaskDAO taskDAO) {
        this.stepDAO = stepDAO;
        this.stepDescriptionDAO = stepDescriptionDAO;
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Step> generateTaskSteps(UUID taskId) {
        taskDAO.taskExists(taskId);
        if(!taskDAO.taskExists(taskId)) {
            throw new RuntimeException("Task with id: " + taskId + "not found");
        }

        Task task = taskDAO.findTaskById(taskId).get();
        List<StepDescription> stepDescriptions = stepDescriptionDAO.getStepDescriptions(task.getTaskBody());
        List<Step> steps = new ArrayList<>();

        stepDescriptions.forEach(stepDesc -> {
            Step step = Step.createForRequest(taskId, stepDesc.getNumber(), stepDesc.getDescription());
            steps.add(step);
            stepDAO.saveStep(step);
        });

        return steps;
    }

    @Override
    public Step addStep(UUID taskId, CreateStepRequest request) {
        Step step = request.convertToStep(taskId);
        return stepDAO.saveStep(step);
    }

    @Override
    public List<Step> getStepsByTaskId(UUID taskId) {
        return stepDAO.findStepsByTaskId(taskId);
    }


    @Override
    public Step prioritizeTaskStep(UUID stepId) {
        Optional<Step> stepVerify = stepDAO.findStepById(stepId);

        if (stepVerify.isEmpty())
            throw new IllegalStateException("Couldn't find step with id: " + stepId);

        Step step = stepVerify.get();

        step.setPriority(!step.isPriority());

        return stepDAO.prioritizeStep(step);
    }

    @Override
    public Step finishTaskStep(UUID stepId) {
        Optional<Step> stepVerify = stepDAO.findStepById(stepId);

        if(stepVerify.isEmpty())
            throw new IllegalStateException("Couldn't find step with id: " + stepId);

        Step step = stepVerify.get();

        step.setFinished(!step.isFinished());

        return stepDAO.finishStep(step);
    }

    @Override
    public Step deleteTaskStep(UUID stepId) {
        return null;
    }
}
