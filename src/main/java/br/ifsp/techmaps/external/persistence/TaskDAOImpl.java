package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskBody;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.commit.gateway.CommitDAO;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.stage.gateway.StageDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

import static br.ifsp.techmaps.domain.entities.task.CommitState.*;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final JdbcTemplate jdbcTemplate;
    private final StageDAO stageDao;
    private final CommitDAO commitDAO;
    private final DashboardDAO dashboardDAO;

    @Value("${queries.sql.task-dao.insert.task}")
    private String insertTaskQuery;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    @Value("${queries.sql.task-dao.select.task-by-stage-id}")
    private String selectTasksByStageIdQuery;

    @Value("${queries.sql.task-dao.update.task-repository}")
    private String updateTaskRepositoryAndDateFinishedQuery;

    @Value("${queries.sql.task-dao.update.task-date-finished}")
    private String updateTaskDateFinishedQuery;

    @Value("${queries.sql.task-dao.exists.task-id}")
    private String existsTaskIdQuery;

    public TaskDAOImpl(JdbcTemplate jdbcTemplate, StageDAO stageDao,
                       CommitDAO commitDAO, DashboardDAO dashboardDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.stageDao = stageDao;
        this.commitDAO = commitDAO;
        this.dashboardDAO = dashboardDAO;
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
        List<Task> tasks = jdbcTemplate.query(selectTasksByStageIdQuery,
                this::mapperTaskFromRs, stageId);
        tasks.forEach(task -> task.setTaskCommit(commitDAO.findTaskCommitByTaskId(task.getId()).get()));
        return tasks;
    }

    @Override
    public Task updateTask(Task task) {
        jdbcTemplate.update(updateTaskRepositoryAndDateFinishedQuery, task.getRepository(),
                task.getId());

        return Task.createWithOnlyId(task.getId());
    }

    @Override
    public Task updateDateFinished(Task task) {
        jdbcTemplate.update(updateTaskDateFinishedQuery,
                task.getDate_finished(), task.getId());

        return Task.createWithOnlyId(task.getId());
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
        Dashboard dashboard = dashboardDAO.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));
        TaskCommit taskCommit = commitDAO.findTaskCommitByTaskId(id).get();

        return Task.createFull
                (id, stage, taskBody, repository, dateCreated, dateFinished, dashboard, taskCommit);
    }

}
