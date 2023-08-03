package br.ifsp.techmaps.usecases.step;

import br.ifsp.techmaps.domain.entities.step.Step;

import java.util.*;

public interface StepCRUD {

    Step addNewTaskSteps(UUID taskId);

    List<Step> getStepsByTaskId(UUID taskId);

    Step updateStepNumber(Step step);

    Step prioritizeTaskStep(Step step);

    Step finishTaskStep(Step step);

    Step deleteTaskStep(UUID stepId);

}
