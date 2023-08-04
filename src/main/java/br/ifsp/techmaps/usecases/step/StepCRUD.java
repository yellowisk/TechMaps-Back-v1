package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.web.model.step.request.CreateStepRequest;

import java.util.*;

public interface StepCRUD {

    List<Step> generateTaskSteps(UUID taskId);

    Step addTask(UUID taskId, CreateStepRequest request);

    List<Step> getStepsByTaskId(UUID taskId);

    Step updateStepNumber(Step step);

    Step prioritizeTaskStep(Step step);

    Step finishTaskStep(Step step);

    Step deleteTaskStep(UUID stepId);

}
