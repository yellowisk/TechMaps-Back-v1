package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import br.ifsp.techmaps.web.model.stage.request.CreateStageRequest;
import br.ifsp.techmaps.web.model.stage.request.UpdateStatusRequest;
import br.ifsp.techmaps.web.exception.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class StageCRUDImpl implements StageCRUD {

    private final StageDAO stageDAO;
    private final DashboardDAO dashboardDAO;
    private final RoadmapDAO roadmapDAO;
    private final TaskDAO taskDAO;

    public StageCRUDImpl(StageDAO stageDAO, RoadmapDAO roadmapDAO, TaskDAO taskDAO, DashboardDAO dashboardDAO) {
        this.stageDAO = stageDAO;
        this.roadmapDAO = roadmapDAO;
        this.taskDAO = taskDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public Stage addNewStage(UUID roadmapId, CreateStageRequest request) {

        if(!roadmapDAO.RoadmapExists(roadmapId))
            throw new ResourceNotFoundException("Couldn't find roadmap with id:" + roadmapId);

        if(roadmapDAO.findRoadmapById(roadmapId).get().getStatus().equals(RoadmapStatus.COMPLETE))
            throw new IllegalArgumentException("This Roadmap is already done!");

        Optional<Roadmap> roadmap = roadmapDAO.findRoadmapById(roadmapId);
        int stageNumber = stageDAO.findStagesByRoadmapId(roadmapId).size() + 1;

        Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap.get(),
                request.getTheme(), StageStatus.UNDONE, stageNumber,0);

        if (request.getTheme().getCondition().equals(roadmap.get().getLanguage().getCondition())
                || request.getTheme().getCondition() == "General") {
            return stageDAO.saveStage(stage);
        } else {
            throw new IllegalArgumentException("Theme not compatible with roadmap language");
        }
    }

    @Override
    public List<Stage> addStagesByRoadmapId(UUID roadmapId) {

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).orElseThrow(() ->
                new ResourceNotFoundException("Couldn't find roadmap with id: " + roadmapId));

        if (roadmap.getStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new IllegalArgumentException("This Roadmap is already done!");
        }

        List<Stage> stages = new ArrayList<>();
        List<StageEnum> themes = new ArrayList<>();
        int stageCounter = 1;

        Map<RoadmapLanguage, List<StageEnum>> languageToThemesMap = new HashMap<>();
        languageToThemesMap.put(RoadmapLanguage.JAVA, Arrays.asList(StageEnum.LEARN_INTELLIJ, StageEnum.LEARN_OOP, StageEnum.LEARN_JAVA, StageEnum.LEARN_INTERNET,
                StageEnum.LEARN_MYSQL, StageEnum.LEARN_SPRING, StageEnum.LEARN_WEBSERVERS));
        languageToThemesMap.put(RoadmapLanguage.JAVASCRIPT, Arrays.asList(StageEnum.LEARN_INTERNET, StageEnum.LEARN_VSCODE, StageEnum.LEARN_HTML, StageEnum.LEARN_CSS,
                StageEnum.LEARN_JS, StageEnum.LEARN_NODEJS, StageEnum.LEARN_WEBSERVERS, StageEnum.LEARN_ANGULAR, StageEnum.LEARN_REACT));
        languageToThemesMap.put(RoadmapLanguage.PYTHON, Arrays.asList(StageEnum.LEARN_PYTHON, StageEnum.LEARN_INTERNET, StageEnum.LEARN_MYSQL, StageEnum.LEARN_DJANGO,
                StageEnum.LEARN_WEBSERVERS, StageEnum.LEARN_CLOUD));
        languageToThemesMap.put(RoadmapLanguage.KOTLIN, Arrays.asList(StageEnum.LEARN_INTERNET, StageEnum.LEARN_OOP, StageEnum.LEARN_KOTLIN, StageEnum.LEARN_MYSQL,
                StageEnum.LEARN_ANDROID, StageEnum.LEARN_FIREBASE, StageEnum.LEARN_WEBSERVERS));

        if (languageToThemesMap.containsKey(roadmap.getLanguage())) {
            themes = languageToThemesMap.get(roadmap.getLanguage());
        }

        for (StageEnum theme : themes) {
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, theme,
                    StageStatus.UNDONE, stageCounter++, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);
        }

        roadmap.setStages(stages);

        return roadmap.getStages();
    }

    @Override
    public Stage getStageById(UUID stageId) {

        if(!stageDAO.StageExists(stageId))
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);

        Stage stage = stageDAO.findStageById(stageId).get();
        List<Task> tasks = taskDAO.findAllTasksByStageId(stageId);
        stage.setTasks(tasks);

        return stage;
    }

    @Override
    public List<Stage> getStagesByRoadmapId(UUID roadmapId) {

        if(!roadmapDAO.RoadmapExists(roadmapId))
            throw new ResourceNotFoundException("Couldn't find roadmap with id:" + roadmapId);

        List<Stage> stages = stageDAO.findStagesByRoadmapId(roadmapId);
        stages.forEach(stage -> stage.setTasks(taskDAO.findAllTasksByStageId(stage.getStageId())));

        return stages;
    }

    @Override
    public Stage updateStageCommit(UUID stageId) {

        if(!stageDAO.StageExists(stageId))
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);

        Optional<Stage> opt = stageDAO.findStageById(stageId);

        Stage stage = opt.get();

        List<CommitState> commits = stageDAO.findCommitsByStageId(stageId);
        Integer counter = 0;

        for(CommitState commit : commits)
            if(commit.equals(CommitState.STAGED))
                counter++;

        stage.setStageCommits(counter);

        Stage response = stageDAO.updateStage(stage);

        if(commits.size() == counter)
            stage.setStageStatus(StageStatus.DONE);
            stageDAO.updateStageStatus(stage);

        return response;
    }

    @Override
    public Stage updateStageStatus(UUID roadmapId, UUID stageId, UpdateStatusRequest request) {
        if(!stageDAO.StageExists(stageId))
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);

        List<CommitState> commitStates = stageDAO.findCommitsByStageId(stageId);
        Integer counterCommit = 0;

        for (CommitState commitState : commitStates)
            if (commitState.equals(CommitState.STAGED))
                counterCommit++;

        List<Timestamp> finishedDates = stageDAO.findDateFinishedOfTasksByStageId(stageId);
        Integer counterDates = 0;

        for (Timestamp finishedDate : finishedDates)
            if (finishedDate != null)
                counterDates++;

        StageStatus status = StageStatus.valueOf(request.getStatus());

        if (commitStates.size() == counterCommit && status.equals(StageStatus.UNDONE))
            throw new BadRequestException("You can't change status to UNDONE, because all commits are staged");

        if (finishedDates.size() == counterDates && status.equals(StageStatus.UNDONE))
            throw new BadRequestException("You can't change status to UNDONE, because all tasks are finished");

        Stage stage = stageDAO.findStageById(stageId).get();
        stage.setStageStatus(status);

        return stageDAO.updateStageStatus(stage);
    }

    @Override
    public Stage deleteStageById(UUID roadmapId, UUID stageId) {

        if(!stageDAO.StageExists(stageId)) {
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);
        }

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        Optional<Stage> opt = stageDAO.findStageById(stageId);

        if (roadmap.getStatus().equals(RoadmapStatus.COMPLETE)) {
            throw new RuntimeException("Couldn't delete '" + opt.get().getTheme().name() + "' because its Roadmap is complete");
        }

        stageDAO.deleteStageById(stageId);
        dashboardDAO.refreshDashboard(roadmap.getDashboardId());

        return opt.get();
    }
}
