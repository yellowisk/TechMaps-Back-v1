package br.ifsp.techmaps.usecases.step.gateway;

import br.ifsp.techmaps.domain.entities.step.StepDescription;
import br.ifsp.techmaps.domain.entities.task.TaskBody;

import java.util.List;

public interface StepDescriptionDAO {
    List<StepDescription> getStepDescriptions(TaskBody info);
    
}
