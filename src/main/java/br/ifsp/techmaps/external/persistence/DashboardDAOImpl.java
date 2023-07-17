package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class DashboardDAOImpl implements DashboardDAO {

    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;

    public DashboardDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Value("${queries.sql.dashboard-dao.insert.dashboard}")
    private String insertDashboardQuery;

    @Value("${queries.sql.dashboard-dao.select.dashboard-by-id}")
    private String selectDashboardByIdQuery;

    @Value("${queries.sql.dashboard-dao.update.dashboard-total-tasks}")
    private String updateTotalTasksQuery;

    @Value("${queries.sql.dashboard-dao.update.dashboard-total-commits}")
    private String updateTotalCommitsQuery;

    @Value("${queries.sql.dashboard-dao.update.dashboard-total-roadmaps-and-total_time}")
    private String updateTotalRoadmapsAndTotalTimeQuery;

    @Override
    public Dashboard saveNewDashboard(UUID dashboardId) {
        jdbcTemplate.update(insertDashboardQuery, dashboardId, 0, 0, 0, null);
        return Dashboard.createWithAllFields(dashboardId, 0, 0, 0, null);
    }

    @Override
    public Optional<Dashboard> findDashboardById(UUID dashboardId) {
        try {
            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByIdQuery, this::mapperDashboardFromRs,
                    dashboardId);

            if (Objects.isNull(dashboard)) {
                throw new IllegalStateException("Dashboard not found");
            }

            return Optional.of(dashboard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Dashboard updateTotalTasks(UUID dashboardId, List<Task> tasks) {
        Dashboard dashboard = Dashboard.createWithOnlyId(dashboardId);
        dashboard.setTotalTasks(tasks.size());
        jdbcTemplate.update(updateTotalTasksQuery, tasks.size(), dashboardId);
        return dashboard;
    }

    @Override
    public Dashboard updateTotalCommits(UUID dashboardId, List<TaskCommit> commits) {
        Dashboard dashboard = Dashboard.createWithOnlyId(dashboardId);
        dashboard.setTotalCommits(commits.size());
        jdbcTemplate.update(updateTotalCommitsQuery, commits.size(), dashboardId);
        return dashboard;
    }

    @Override
    public Dashboard updateTotalRoadmapsAndTotalTime(UUID dashboardId, List<Roadmap> roadmaps,
                                                     Long totalTime) {
        jdbcTemplate.update(updateTotalRoadmapsAndTotalTimeQuery, roadmaps.size(), totalTime, dashboardId);
        return Dashboard.createWithOnlyId(dashboardId);
    }

    public Dashboard mapperDashboardFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID dashboardId = (UUID) rs.getObject("id");
        int totalRoadmaps = rs.getInt("total_roadmaps");
        int totalTasks = rs.getInt("total_tasks");
        int totalCommits = rs.getInt("total_commits");
        Long totalTime = rs.getLong("total_time");
        return Dashboard.createWithAllFields(dashboardId, totalRoadmaps, totalTasks,
                totalCommits, totalTime);
    }
}
