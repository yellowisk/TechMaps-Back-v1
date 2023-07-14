package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.Task;
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

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final JdbcTemplate jdbcTemplate;
    private final StageDAO stageDao;
    private final DashboardDAO dashboardDao;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    @Value("${queries.sql.task-dao.insert.task}")
    private String insertTaskQuery;

    @Value("${queries.sql.task-dao.select.task-by-stage-id}")
    private String selectTasksByStageIdQuery;

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
    public List<Task> findAllTasksByStageId(UUID stageId) {
        return jdbcTemplate.query(selectTasksByStageIdQuery,
                this::mapperTaskFromRs, stageId);
    }

    @Override
    public Optional<Task> findTaskById(UUID taskId) {
        try {
            Task task = jdbcTemplate.queryForObject(selectTaskByIdQuery, (rs, rowNum) -> {
                UUID id = (UUID) rs.getObject("id");
                return Task.createWithOnlyId(id);
            }, taskId);
            return Optional.of(task);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Task> findTasksByRoadmapId(UUID roadmapId) {
        return Optional.empty();
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public TaskCommit createTaskCommit(Task task) {
        return null;
    }

    @Override
    public TaskCommit updateTaskCommmit(UUID taskCommitId) {
        return null;
    }

    @Override
    public Boolean TaskExists(UUID taskId) {
        try {
            jdbcTemplate.queryForObject(selectTaskByIdQuery, (rs, rowNum) -> null, taskId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private Task mapperTaskFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID stageId = (UUID) rs.getObject("stage_id");
        String repository = rs.getString("repository_link");
        Timestamp dateCreated = rs.getTimestamp("date_created");
        Timestamp dateFinished = rs.getTimestamp("date_finished");
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        Stage stage = stageDao.findStageById(stageId).orElseThrow(() -> new SQLDataException("Stage not found"));
        Dashboard dashboard = dashboardDao.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));

        return Task.createwithoutTaskCommit
                (id, stage, repository, dateCreated, dateFinished, dashboard);
    }

}
