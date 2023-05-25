package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private final JdbcTemplate jdbcTemplate;

    @Value("${queries.sql.task-dao.select.task-by-id}")
    private String selectTaskByIdQuery;

    public TaskDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Optional<Task> findTaskById(UUID taskId) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAllTasks() {
        return null;
    }

    @Override
    public Task saveNewTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }
}
