package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.exception.ResourceNotFoundException;
import br.ifsp.techmaps.web.model.stage.request.UpdateStageRequest;
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

        System.out.println(stage.getRoadmap());

        if (request.getTheme().getCondition().equals(roadmap.get().getRoadmapLanguage().getCondition())
                || request.getTheme().getCondition() == "General") {
            return stageDAO.saveStage(stage);
        } else {
            throw new IllegalArgumentException("Theme not compatible with roadmap language");
        }
    }

    @Override
    public List<Stage> addStagesByRoadmapId(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.JAVA)) {
            List<Stage> stages = new ArrayList<>(8);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_JAVA, StageStatus.UNDONE, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);

            List<StageEnum> backendThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Backend") && learn != StageEnum.LEARN_JAVA
                && learn != StageEnum.LEARN_PYTHON) {
                    backendThemes.add(learn);
                }
            }

            System.out.println("----->" + backendThemes);

            for (int i = 0; i < 7; i++) {
                Stage stageBack = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null,
                        StageStatus.UNDONE, 0);
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

            System.out.println("----->" + frontendThemes);

            for (int i = 0; i < 4; i++) {
                Stage stageFront = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null,
                        StageStatus.UNDONE, 0);

                if (i < frontendThemes.size()) {
                    StageEnum theme = frontendThemes.get(i);
                    stageFront.setTheme(theme);
                }

                stages.add(stageFront);
                stageDAO.saveStage(stageFront);
            }
            roadmap.setStages(stages);
        }

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.PYTHON)) {
            List<Stage> stages = new ArrayList<>(8);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_PYTHON, StageStatus.UNDONE, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);

            List<StageEnum> backendThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Backend") && learn != StageEnum.LEARN_PYTHON
                && learn != StageEnum.LEARN_JAVA) {
                    backendThemes.add(learn);
                }
            }

            System.out.println("----->" + backendThemes);

            for (int i = 0; i < 7; i++) {
                Stage stageBack = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null,
                        StageStatus.UNDONE, 0);
                if (i < backendThemes.size()) {
                    StageEnum theme = backendThemes.get(i);
                    stageBack.setTheme(theme);
                }
                stages.add(stageBack);
                stageDAO.saveStage(stageBack);
            }
            roadmap.setStages(stages);
        }

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.KOTLIN)) {
            List<Stage> stages = new ArrayList<>(10);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_KOTLIN, StageStatus.UNDONE, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);

            List<StageEnum> backendThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Backend") && learn != StageEnum.LEARN_PYTHON) {
                    backendThemes.add(learn);
                }
            }

            System.out.println("----->" + backendThemes);

            for (int i = 0; i < 8; i++) {
                Stage stageBack = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null,
                        StageStatus.UNDONE, 0);
                if (i < backendThemes.size()) {
                    StageEnum theme = backendThemes.get(i);
                    stageBack.setTheme(theme);
                }
                stages.add(stageBack);
                stageDAO.saveStage(stageBack);
            }

            List<StageEnum> androidThemes = new ArrayList<>();

            for (StageEnum learn : StageEnum.values()) {
                if (learn.getCondition().equals("Android") && learn != StageEnum.LEARN_KOTLIN) {
                    androidThemes.add(learn);
                }
            }

            System.out.println("----->" + androidThemes);

            for (int i = 0; i < 1; i++) {
                Stage stageAndroid = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null,
                        StageStatus.UNDONE, 0);
                if (i < androidThemes.size()) {
                    StageEnum theme = androidThemes.get(i);
                    stageAndroid.setTheme(theme);
                }
                stages.add(stageAndroid);
                stageDAO.saveStage(stageAndroid);
            }
            roadmap.setStages(stages);
        }

        return roadmap.getStages();
    }

    @Override
    public Stage getStageById(UUID stageId) {
        Optional<Stage> opt = stageDAO.findStageById(stageId);

        if (opt.isEmpty()) {
            ResourceNotFoundException excpt =
                    new ResourceNotFoundException("Stage not found");
        }

        return stageDAO.findStageById(stageId).get();
    }

    @Override
    public List<Stage> getStagesByRoadmapId(UUID roadmapId) {
        Optional<Roadmap> opt = roadmapDAO.findRoadmapById(roadmapId);

        if (opt.isEmpty()) {
            ResourceNotFoundException excpt =
                    new ResourceNotFoundException("Roadmap not found");
        }

        return stageDAO.findStagesByRoadmapId(roadmapId);
//        return roadmapDAO.findRoadmapById(roadmapId).get().getStages();
    }

    @Override
    public Stage updateStage(UUID stageId, UpdateStageRequest request) {
        Optional<Stage> opt = stageDAO.findStageById(stageId);

        if (opt.isEmpty()) {
            ResourceNotFoundException excpt =
                    new ResourceNotFoundException("Couldn't find stage with id: " + stageId);
        }

        Stage stage = request.convertToStage();

        return stageDAO.updateStage(stage.getNewInstanceWithId(stageId));
    }
}
