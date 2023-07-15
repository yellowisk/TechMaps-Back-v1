package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskBody;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;

import java.sql.*;
import java.util.*;

import static br.ifsp.techmaps.domain.entities.task.CommitState.*;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final JdbcTemplate jdbcTemplate;
    private final StageDAO stageDao;
    private final DashboardDAO dashboardDao;

    @Value("${queries.sql.task-dao.exists.task-id}")
    private String existsTaskIdQuery;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    @Value("${queries.sql.task-dao.select.task-by-stage-id}")
    private String selectTasksByStageIdQuery;

    @Value("${queries.sql.task-dao.insert.task}")
    private String insertTaskQuery;

    @Value("${queries.sql.task-commit-dao.insert.task-commit}")
    private String insertTaskCommitQuery;

    @Value("${queries.sql.task-commit-dao.select.task-commit-by-id}")
    private String selectTaskCommitByIdQuery;

    @Value("${queries.sql.task-dao.update.task-repository-and-date-finished}")
    private String updateTaskRepositoryAndDateFinishedQuery;

    @Value("${queries.sql.task-commit-dao.update.task-commit-state}")
    private String updateTaskCommitStatusQuery;

    public TaskDAOImpl(JdbcTemplate jdbcTemplate, StageDAO stageDao, DashboardDAO dashboardDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.stageDao = stageDao;
        this.dashboardDao = dashboardDao;
    }


    @Override
    public Task saveNewTask(Task task) {
        jdbcTemplate.update(insertTaskQuery, task.getId(), task.getStage().getStageId(),
                task.getStage().getTheme().name(), task.getTaskBody().name(), task.getRepository(),
                task.getDate_created(), task.getDate_finished(), task.getDashboard().getDashboardId());
        return Task.createWithOnlyId(task.getId());
    }

    @Override
    public Optional<Task> findTaskById(UUID taskId) {
        Task task;
        try {
            task = jdbcTemplate.queryForObject(selectTaskByIdQuery,
                this::mapperTaskFromRs, taskId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        if(Objects.isNull(task))
            throw new IllegalStateException();

        return Optional.of(task);
    }

    @Override
    public List<Task> findAllTasksByStageId(UUID stageId) {
        return jdbcTemplate.query(selectTasksByStageIdQuery,
                this::mapperTaskFromRs, stageId);
    }

    @Override
    public Task updateTask(Task task) {
        jdbcTemplate.update(updateTaskRepositoryAndDateFinishedQuery, task.getRepository(),
                task.getDate_finished(), task.getId());
        return Task.createWithOnlyId(task.getId());
    }

    @Override
    public Optional<TaskCommit> findTaskCommitById(UUID taskCommitId) {
        TaskCommit taskCommit;
        try {
            taskCommit = jdbcTemplate.queryForObject(selectTaskCommitByIdQuery, this::mapperTaskCommitFromRs, taskCommitId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        if(Objects.isNull(taskCommit))
            throw new IllegalStateException();

        return Optional.of(taskCommit);
    }

    @Override
    public TaskCommit createTaskCommit(Task task) {
        UUID taskCommitId = UUID.randomUUID();
        jdbcTemplate.update(insertTaskCommitQuery, taskCommitId,
                task.getId(), TaskCommit.createCommitTag(task),
                UNSTAGED.name(), task.getDashboard().getDashboardId());
        return TaskCommit.createWithOnlyId(taskCommitId);
    }

    @Override
    public TaskCommit updateTaskCommmit(UUID taskCommitId) {
        jdbcTemplate.update(updateTaskCommitStatusQuery, STAGED.name(), taskCommitId);
        return TaskCommit.createWithOnlyId(taskCommitId);
    }

    @Override
    public Boolean TaskExists(UUID taskId) {
        Boolean exists = jdbcTemplate.queryForObject(existsTaskIdQuery, Boolean.class, taskId);
        return Objects.nonNull(exists) && exists;
    }

    private Task mapperTaskFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID stageId = (UUID) rs.getObject("stage_id");
        TaskBody taskBody = TaskBody.valueOf(rs.getString("info"));
        String repository = rs.getString("repository_link");
        Timestamp dateCreated = rs.getTimestamp("date_created");
        Timestamp dateFinished = rs.getTimestamp("date_finished");
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        Stage stage = stageDao.findStageById(stageId).orElseThrow(() -> new SQLDataException("Stage not found"));
        Dashboard dashboard = dashboardDao.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));

        return Task.createwithoutTaskCommit
                (id, stage, taskBody, repository, dateCreated, dateFinished, dashboard);
    }

    private TaskCommit mapperTaskCommitFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID taskId = (UUID) rs.getObject("task_id");
        String commitTag = rs.getString("tag");
        CommitState commitState = CommitState.valueOf(rs.getString("state"));
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        Task task = findTaskById(taskId).orElseThrow(() -> new SQLDataException("Task not found"));
        Dashboard dashboard = dashboardDao.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));

        return new TaskCommit(id, task, commitTag, commitState);
    }

}
