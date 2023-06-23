package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StageCRUDImpl implements StageCRUD {

    private final StageDAO stageDAO;
    private final RoadmapDAO roadmapDAO;

    public StageCRUDImpl(StageDAO stageDAO, RoadmapDAO roadmapDAO) {
        this.stageDAO = stageDAO;
        this.roadmapDAO = roadmapDAO;
    }

    @Override
    public Boolean StageExists(UUID stageId) {
        return null;
    }

    @Override
    public Stage addNewStage(UUID roadmapId, CreateStageRequest request) {

        Optional<Roadmap> roadmap = roadmapDAO.findRoadmapById(roadmapId);

        Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap.get(),
                request.getTheme(), StageStatus.UNDONE, 0);

        return stageDAO.saveStage(stage);
    }

    @Override
    public List<Stage> addStagesByRoadmapId(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.JAVA)) {
            List<Stage> stages = new ArrayList<>(10);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_JAVA, StageStatus.UNDONE, 0);

            stageDAO.saveStage(stage);

            for (int i = 0; i < 9; i++) {
                Stage stageBack = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null, StageStatus.UNDONE, 0);

                for (StageEnum skill : StageEnum.values()) {
                    if (skill.getCondition().equals("Backend")) {
                        stageBack.setTheme(skill);
                    }
                }
                stages.add(stage);
                stageDAO.saveStage(stageBack);
            }
            roadmap.setStages(stages);
        }
        return roadmap.getStages();
    }

    @Override
    public Stage getStageById(UUID stageId) {
        return null;
    }

    @Override
    public List<Stage> getStagesByRoadmapId(UUID roadmapId) {
        return null;
    }
}
