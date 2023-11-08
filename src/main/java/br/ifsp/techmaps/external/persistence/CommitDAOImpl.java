package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.commit.gateway.CommitDAO;

import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class CommitDAOImpl implements CommitDAO {

    private final JdbcTemplate jdbcTemplate;

    private final DashboardDAO dashboardDAO;

    public CommitDAOImpl(JdbcTemplate jdbcTemplate, DashboardDAO dashboardDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.dashboardDAO = dashboardDAO;
    }

    @Value("${queries.sql.task-commit-dao.insert.task-commit}")
    private String insertTaskCommitQuery;
    @Value("${queries.sql.task-commit-dao.select.task-commit-by-task-with-stage-id}")
    private String selectTaskCommitByTaskWithStageIdQuery;
    @Value("${queries.sql.task-commit-dao.select.task-commit-by-id}")
    private String selectTaskCommitByIdQuery;
    @Value("${queries.sql.task-commit-dao.select.task-commits-by-dashboard-id}")
    private String selectTaskCommitsByDashboardIdQuery;
    @Value("${queries.sql.task-commit-dao.select.task-commit-by-task-id}")
    private String selectTaskCommitByTaskIdQuery;
    @Value("${queries.sql.task-commit-dao.update.task-commit-status}")
    private String updateTaskCommitStatusQuery;

    @Override
    public TaskCommit createTaskCommit(Task task) {
        UUID taskCommitId = UUID.randomUUID();
        jdbcTemplate.update(insertTaskCommitQuery, taskCommitId,
                task.getTaskId(), TaskCommit.createCommitTag(task),
                false, task.getDashboard().getDashboardId());
        return TaskCommit.createWithOnlyId(taskCommitId);
    }

    @Override
    public Optional<TaskCommit> findTaskCommitById(UUID taskCommitId) {
        TaskCommit taskCommit;
        try {
            taskCommit = jdbcTemplate.queryForObject(selectTaskCommitByIdQuery,
                    this::mapperTaskCommitFromRs, taskCommitId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        if(Objects.isNull(taskCommit))
            throw new IllegalStateException();

        return Optional.of(taskCommit);
    }

    @Override
    public Optional<TaskCommit> findTaskCommitByTaskId(UUID taskId) {
        TaskCommit taskCommit;
        try {
            taskCommit = jdbcTemplate.queryForObject(selectTaskCommitByTaskIdQuery, this::mapperTaskCommitFromRs, taskId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        if(Objects.isNull(taskCommit))
            throw new IllegalStateException("Couldn't find TaskCommit with task id: " + taskId);

        return Optional.of(taskCommit);
    }

    @Override
    public List<TaskCommit> commitsByStageId(UUID stageId) {
        return jdbcTemplate.query(selectTaskCommitByTaskWithStageIdQuery, this::mapperTaskCommitFromRs, stageId);
    }

    @Override
    public List<TaskCommit> commitsByDashboardId(UUID dashboardId) {
        List<TaskCommit> commits = jdbcTemplate.query(selectTaskCommitsByDashboardIdQuery,
                this::mapperTaskCommitFromRs, dashboardId);
        return commits;
    }

    @Override
    public TaskCommit updateTaskCommmit(TaskCommit taskCommit) {
        jdbcTemplate.update(updateTaskCommitStatusQuery, taskCommit.isStaged(),
                taskCommit.getCommitId());
        dashboardDAO.refreshDashboard(taskCommit.getTask().getDashboard().getDashboardId());
        return TaskCommit.createWithOnlyId(taskCommit.getCommitId());
    }

    private TaskCommit mapperTaskCommitFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID taskId = (UUID) rs.getObject("task_id");
        String commitTag = rs.getString("tag");
        Boolean isStaged = rs.getBoolean("is_staged");
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        Dashboard dashboard = dashboardDAO.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));
        Task task = Task.createWithIdAndDashboard(taskId, dashboard);

        return new TaskCommit(id, task, commitTag, isStaged);
    }

}
