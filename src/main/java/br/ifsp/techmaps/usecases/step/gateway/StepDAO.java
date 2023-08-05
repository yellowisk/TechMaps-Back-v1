package br.ifsp.techmaps.usecases.step.gateway;

import java.util.*;
import br.ifsp.techmaps.domain.entities.step.Step;
import br.ifsp.techmaps.domain.entities.step.StepDescription;
import br.ifsp.techmaps.domain.entities.task.TaskBody;

public interface StepDAO {
    Boolean StepExists (UUID stepId);
    Step saveStep(Step step);
    Optional<Step> findStepById(UUID stepId);
    List<Step> findStepsByTaskId(UUID taskId);
    Step prioritizeStep(Step step);
    Step finishStep(Step step);
    Step deleteStepById(UUID stepId);

}
