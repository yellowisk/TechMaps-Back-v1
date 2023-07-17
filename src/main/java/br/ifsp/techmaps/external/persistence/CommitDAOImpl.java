package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.task.CommitState;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.commit.CommitDAO;

import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.usecases.task.gateway.TaskDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import static br.ifsp.techmaps.domain.entities.task.CommitState.UNSTAGED;

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
    @Value("${queries.sql.task-commit-dao.update.task-commit-state}")
    private String updateTaskCommitStatusQuery;

    @Override
    public TaskCommit createTaskCommit(Task task) {
        UUID taskCommitId = UUID.randomUUID();
        jdbcTemplate.update(insertTaskCommitQuery, taskCommitId,
                task.getId(), TaskCommit.createCommitTag(task),
                UNSTAGED.name(), task.getDashboard().getDashboardId());
        return TaskCommit.createWithOnlyId(taskCommitId);
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
    public List<TaskCommit> commitsByStageId(UUID stageId) {
        return jdbcTemplate.query(selectTaskCommitByTaskWithStageIdQuery, this::mapperTaskCommitFromRs, stageId);
    }

    @Override
    public List<TaskCommit> commitsByDashboardId(UUID dashboardId) {
        List<TaskCommit> commits = jdbcTemplate.query(selectTaskCommitsByDashboardIdQuery,
                this::mapperTaskCommitFromRs, dashboardId);
    }

    @Override
    public TaskCommit updateTaskCommmit(TaskCommit taskCommit) {
        jdbcTemplate.update(updateTaskCommitStatusQuery, taskCommit.getState().name(),
                taskCommit.getCommitId());
        return TaskCommit.createWithOnlyId(taskCommit.getCommitId());
    }

    private TaskCommit mapperTaskCommitFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID taskId = (UUID) rs.getObject("task_id");
        String commitTag = rs.getString("tag");
        CommitState commitState = CommitState.valueOf(rs.getString("state"));
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        Dashboard dashboard = dashboardDAO.findDashboardById(dashboardId).orElseThrow(() -> new SQLDataException("Dashboard not found"));

        return new TaskCommit(id,commitTag, commitState);
    }

}
