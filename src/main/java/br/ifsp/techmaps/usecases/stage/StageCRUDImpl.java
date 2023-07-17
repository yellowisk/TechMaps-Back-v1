package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStatusRequest;
import br.ifsp.techmaps.web.exception.*;
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
    public Stage addNewStage(UUID roadmapId, CreateStageRequest request) {

        if(!roadmapDAO.RoadmapExists(roadmapId)) {
            throw new ResourceNotFoundException("Couldn't find roadmap with id:" + roadmapId);
        }

        Optional<Roadmap> roadmap = roadmapDAO.findRoadmapById(roadmapId);

        Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap.get(),
                request.getTheme(), StageStatus.UNDONE, 0);

        if (request.getTheme().getCondition().equals(roadmap.get().getRoadmapLanguage().getCondition())
                || request.getTheme().getCondition() == "General") {
            return stageDAO.saveStage(stage);
        } else {
            throw new IllegalArgumentException("Theme not compatible with roadmap language");
        }
    }

    @Override
    public List<Stage> addStagesByRoadmapId(UUID roadmapId) {

        if(!roadmapDAO.RoadmapExists(roadmapId)) {
            throw new ResourceNotFoundException("Couldn't find roadmap with id:" + roadmapId);
        }

        if(roadmapDAO.findRoadmapById(roadmapId).get().getRoadmapStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new IllegalArgumentException("This Roadmap is already done!");
        }

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

        if(!stageDAO.StageExists(stageId)) {
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);
        }

        return stageDAO.findStageById(stageId).get();
    }

    @Override
    public List<Stage> getStagesByRoadmapId(UUID roadmapId) {

        if(!roadmapDAO.RoadmapExists(roadmapId)) {
            throw new ResourceNotFoundException("Couldn't find roadmap with id:" + roadmapId);
        }

        return stageDAO.findStagesByRoadmapId(roadmapId);
    }

    @Override
    public Stage updateStageCommit(UUID stageId) {

        if(!stageDAO.StageExists(stageId)) {
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);
        }

        Optional<Stage> opt = stageDAO.findStageById(stageId);

        Stage stage = opt.get();

        List<CommitState> commits = stageDAO.findCommitsByStageId(stageId);
        Integer counter = 0;

        for(CommitState commit : commits) {
            if(commit.equals(CommitState.STAGED)) {
                counter++;
            }
        }

        stage.setStageCommit(counter);

        Stage response = stageDAO.updateStage(stage);

        if(commits.size() == counter) {
            stage.setStageStatus(StageStatus.DONE);
            stageDAO.updateStageStatus(stage);
        }

        return response;
    }

    @Override
    public Stage updateStageStatus(UUID roadmapId, UUID stageId, UpdateStatusRequest request) {
        if(!stageDAO.StageExists(stageId)) {
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);
        }

        Stage stage = stageDAO.findStageById(stageId).get();
        stage.setStageStatus(request.getStatus());

        List<CommitState> commitStates = stageDAO.findCommitsByStageId(stageId);
        Integer counter = 0;

        for (CommitState commitState : commitStates) {
            if (commitState.equals(CommitState.STAGED)) {
                counter++;
            }
        }

        if (commitStates.size() == counter && request.getStatus().equals(StageStatus.UNDONE)) {
            BadRequestException badRequestException = new
                    BadRequestException("You can't change status to UNDONE, because all commits are staged");
        }
        return stageDAO.updateStageStatus(stage);
    }

}
