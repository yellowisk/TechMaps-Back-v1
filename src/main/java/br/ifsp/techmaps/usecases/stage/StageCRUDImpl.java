package br.ifsp.techmaps.usecases.stage;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageTheme;
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

        if(roadmapDAO.findRoadmapById(roadmapId).get().isCompleted())
            throw new IllegalArgumentException("This Roadmap is already done!");

        Optional<Roadmap> roadmap = roadmapDAO.findRoadmapById(roadmapId);
        int stageNumber = stageDAO.findStagesByRoadmapId(roadmapId).size() + 1;

        Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap.get(),
                request.getTheme(), false, stageNumber,0);

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

        if (roadmap.isCompleted()) {
            throw new IllegalArgumentException("This Roadmap is already done!");
        }

        List<Stage> stages = new ArrayList<>();
        List<StageTheme> themes = new ArrayList<>();
        int stageCounter = 1;

        Map<RoadmapLanguage, List<StageTheme>> languageToThemesMap = new HashMap<>();
        languageToThemesMap.put(RoadmapLanguage.JAVA, Arrays.asList(StageTheme.LEARN_INTELLIJ, StageTheme.LEARN_OOP, StageTheme.LEARN_JAVA, StageTheme.LEARN_INTERNET,
                StageTheme.LEARN_SQL, StageTheme.LEARN_SPRING, StageTheme.LEARN_WEBSERVERS));
        languageToThemesMap.put(RoadmapLanguage.JAVASCRIPT, Arrays.asList(StageTheme.LEARN_INTERNET, StageTheme.LEARN_VSCODE, StageTheme.LEARN_HTML, StageTheme.LEARN_CSS,
                StageTheme.LEARN_JS, StageTheme.LEARN_NODEJS, StageTheme.LEARN_WEBSERVERS, StageTheme.LEARN_ANGULAR, StageTheme.LEARN_REACT));
        languageToThemesMap.put(RoadmapLanguage.PYTHON, Arrays.asList(StageTheme.LEARN_PYTHON, StageTheme.LEARN_INTERNET, StageTheme.LEARN_SQL, StageTheme.LEARN_DJANGO,
                StageTheme.LEARN_WEBSERVERS, StageTheme.LEARN_CLOUD));
        languageToThemesMap.put(RoadmapLanguage.KOTLIN, Arrays.asList(StageTheme.LEARN_INTERNET, StageTheme.LEARN_OOP, StageTheme.LEARN_KOTLIN, StageTheme.LEARN_SQL,
                StageTheme.LEARN_ANDROID, StageTheme.LEARN_FIREBASE, StageTheme.LEARN_WEBSERVERS));

        if (languageToThemesMap.containsKey(roadmap.getLanguage())) {
            themes = languageToThemesMap.get(roadmap.getLanguage());
        }

        for (StageTheme theme : themes) {
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, theme,
                    false, stageCounter++, 0);
            stages.add(stage);
            stageDAO.saveStage(stage);
        }

        return stages;
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

        List<Boolean> commits = stageDAO.findCommitsByStageId(stageId);
        Integer counter = 0;

        for(boolean commit : commits)
            if(commit)
                counter++;

        stage.setStageCommits(counter);

        Stage response = stageDAO.updateStage(stage);

        if(commits.size() == counter) {
            stage.setIsDone(true);
            stageDAO.updateStageStatus(stage);
        }

        return response;
    }

    @Override
    public Stage updateStageStatus(UUID roadmapId, UUID stageId, UpdateStatusRequest request) {
        if(!stageDAO.StageExists(stageId))
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);

        List<Boolean> commitStates = stageDAO.findCommitsByStageId(stageId);
        Integer counterCommit = 0;

        for (boolean commitState : commitStates)
            if (commitState)
                counterCommit++;

        List<Timestamp> finishedDates = stageDAO.findDateFinishedOfTasksByStageId(stageId);
        Integer counterDates = 0;

        for (Timestamp finishedDate : finishedDates)
            if (finishedDate != null)
                counterDates++;

        boolean isDone = request.isDone();

        if (commitStates.size() == counterCommit && isDone == false)
            throw new BadRequestException("You can't change status to UNDONE, because all commits are staged");

        if (finishedDates.size() == counterDates && isDone == false)
            throw new BadRequestException("You can't change status to UNDONE, because all tasks are finished");

        Stage stage = stageDAO.findStageById(stageId).get();
        stage.setIsDone(isDone);

        return stageDAO.updateStageStatus(stage);
    }

    @Override
    public Stage deleteStageById(UUID roadmapId, UUID stageId) {

        if(!stageDAO.StageExists(stageId)) {
            throw new ResourceNotFoundException("Couldn't find stage with id:" + stageId);
        }

        Roadmap roadmap = roadmapDAO.findRoadmapById(roadmapId).get();

        Optional<Stage> opt = stageDAO.findStageById(stageId);

        if (roadmap.isCompleted()) {
            throw new RuntimeException("Couldn't delete '" + opt.get().getTheme().name() + "' because its Roadmap is complete");
        }

        stageDAO.deleteStageById(stageId);
        dashboardDAO.refreshDashboard(roadmap.getDashboardId());

        return opt.get();
    }
}
