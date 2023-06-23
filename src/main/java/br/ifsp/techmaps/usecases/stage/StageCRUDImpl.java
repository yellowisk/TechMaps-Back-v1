package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.stage.StageType;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

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

        if (request.getTheme().getCondition().equals(roadmap.get().getRoadmapLanguage().getCondition())) {
            return stageDAO.saveStage(stage);
        } else {
            throw new IllegalArgumentException("Theme not compatible with roadmap language");
        }
    }

    @Override
    public List<Stage> addStagesByRoadmapId(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.JAVA)) {
            List<Stage> stages = new ArrayList<>(9);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_JAVA, StageStatus.UNDONE, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);

            List<StageEnum> backendThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Backend") && learn != StageEnum.LEARN_JAVA) {
                    backendThemes.add(learn);
                }
            }

            for (int i = 0; i < 8; i++) {
                Stage stageBack = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null, StageStatus.UNDONE, 0);

                if (i < backendThemes.size()) {
                    StageEnum theme = backendThemes.get(i);
                    stageBack.setTheme(theme);
                }

                stages.add(stageBack);
                stageDAO.saveStage(stageBack);
            }
            roadmap.setStages(stages);
        }

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.JAVASCRIPT)) {
            List<Stage> stages = new ArrayList<>();
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_JS, StageStatus.UNDONE, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);

            List<StageEnum> frontendThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Frontend") && learn != StageEnum.LEARN_JS) {
                    frontendThemes.add(learn);
                }
            }

            for (int i = 0; i < 8; i++) {
                Stage stageFront = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null, StageStatus.UNDONE, 0);

                if (i < frontendThemes.size()) {
                    StageEnum theme = frontendThemes.get(i);
                    stageFront.setTheme(theme);
                }

                stages.add(stageFront);
                stageDAO.saveStage(stageFront);
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
