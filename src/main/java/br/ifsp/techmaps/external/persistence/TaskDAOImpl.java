package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    @Value("${queries.sql.task-dao.insert.task}")
    private String insertTaskQuery;

    @Value("${queries.sql.task-dao.select.tasks-all}")
    private String selectAllTasksQuery;

    @Value("${queries.sql.task-dao.select.tasks-by-roadmap-id}")
    private String selectTasksByRoadmapIdQuery;

    public TaskDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Task saveNewTask(Task task) {
        UUID taskId = UUID.randomUUID();
        jdbcTemplate.update(insertTaskQuery, taskId, task.getStage(), task.getTitle(), task.getTitle(), task.getDate(), task.getHour());
            return Task.createWithOnlyId(taskId);
    }

    @Override
    public List<Task> findAllTasks(UUID stageId) {
        List<Task> tasks = jdbcTemplate.query(selectAllTasksQuery, (rs, rowNum) -> {
            Task task = new Task();
            task.setTaskId(UUID.fromString(rs.getString("id")));
            Stage.createStageWithOnlyId((UUID) rs.getObject("stage_id"));
            task.setTitle(rs.getString("title"));
            task.setDate(rs.getDate("date"));
            task.setHour(rs.getTime("hour"));
            return task;
        }, stageId);

        return tasks;
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
}
