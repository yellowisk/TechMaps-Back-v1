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

@Repository
public class CommitDAOImpl implements CommitDAO {

    private final JdbcTemplate jdbcTemplate;

    private final DashboardDAO dashboardDAO;

    public CommitDAOImpl(JdbcTemplate jdbcTemplate, DashboardDAO dashboardDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.dashboardDAO = dashboardDAO;
    }

    @Value("${queries.sql.task-commit-dao.select.task-commit-by-task-with-stage-id}")
    private String selectTaskCommitByTaskWithStageIdQuery;

    @Override
    public Optional<TaskCommit> findTaskCommitById(UUID taskCommitId) {
        return Optional.empty();
    }

    @Override
    public List<TaskCommit> commitsByStageId(UUID stageId) {
        return jdbcTemplate.query(selectTaskCommitByTaskWithStageIdQuery, this::mapperTaskCommitFromRs, stageId);
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
