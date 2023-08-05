package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.web.model.step.request.CreateStepRequest;

import java.util.*;

public interface StepCRUD {

    List<Step> generateTaskSteps(UUID taskId);

    Step addStep(UUID taskId, CreateStepRequest request);

    List<Step> getStepsByTaskId(UUID taskId);

    Step prioritizeTaskStep(UUID stepId);

    Step finishTaskStep(UUID stepId);

    Step deleteTaskStep(UUID stepId);

}
