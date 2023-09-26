package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.task.Task;
import br.ifsp.techmaps.domain.entities.task.TaskCommit;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
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
    private final RoadmapDAO roadmapDAO;

    public DashboardDAOImpl(JdbcTemplate jdbcTemplate, RoadmapDAO roadmapDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.roadmapDAO = roadmapDAO;
    }

    @Value("${queries.sql.dashboard-dao.insert.dashboard}")
    private String insertDashboardQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-id}")
    private String selectDashboardByIdQuery;
    @Value("${queries.sql.dashboard-dao.select.dashboard-by-user-id}")
    private String selectDashboardByUserIdQuery;
    @Value("${queries.sql.dashboard-dao.select.count-completed-roadmaps-by-dashboard-id}")
    private String selectCountCompleteRoadmapsByDashboardIdQuery;
    @Value("${queries.sql.dashboard-dao.select.count-tasks-with-date-finished-by-dashboard-id}")
    private String selectCountFinishedTasksByDashboardIdQuery;
    @Value("${queries.sql.dashboard-dao.select.count-commits-with-staged-state-by-dashboard-id}")
    private String selectCountStagedCommitsByDashboardIdQuery;
    @Value("${queries.sql.dashboard-dao.select.count-total-time-from-roadmaps-by-dashboard-id}")
    private String selectCountTotalTimeFromRoadmapsByDashboardIdQuery;
    @Value("${queries.sql.dashboard-dao.update.dashboard-total-tasks}")
    private String updateTotalTasksQuery;
    @Value("${queries.sql.dashboard-dao.update.dashboard-total-commits}")
    private String updateTotalCommitsQuery;
    @Value("${queries.sql.dashboard-dao.update.dashboard-total-roadmaps-and-total_time}")
    private String updateTotalRoadmapsAndTotalTimeQuery;
    @Value("${queries.sql.dashboard-dao.exists.dashboard-by-id}")
    private String existsDashboardByIdQuery;

    @Override
    public Dashboard saveNewDashboard(UUID dashboardId, UUID userId) {
        jdbcTemplate.update(insertDashboardQuery, dashboardId, 0, 0, 0, null, userId);
        return Dashboard.createWithAllFields(dashboardId, 0, 0, 0, null, userId);
    }

    @Override
    public Dashboard refreshDashboard(UUID dashboardId) {
        try {
            int countRoadmaps = jdbcTemplate.queryForObject(selectCountCompleteRoadmapsByDashboardIdQuery,
                    Integer.class, dashboardId);
            int countTasks = jdbcTemplate.queryForObject(selectCountFinishedTasksByDashboardIdQuery, Integer.class,
                    dashboardId);
            int countCommits = jdbcTemplate.queryForObject(selectCountStagedCommitsByDashboardIdQuery,
                    Integer.class, dashboardId);
            Long totalTime = jdbcTemplate.queryForObject(selectCountTotalTimeFromRoadmapsByDashboardIdQuery,
                    Long.class, dashboardId);

            jdbcTemplate.update(updateTotalTasksQuery, countTasks, dashboardId);
            jdbcTemplate.update(updateTotalCommitsQuery, countCommits, dashboardId);
            jdbcTemplate.update(updateTotalRoadmapsAndTotalTimeQuery, countRoadmaps, totalTime, dashboardId);

            Dashboard dashboard = jdbcTemplate.queryForObject(
                    selectDashboardByIdQuery, this::mapperDashboardFromRs,
                    dashboardId);

            return Dashboard.createWithAllFields(dashboardId, 0, countTasks,
                    countCommits, totalTime, dashboard.getUserId());
        } catch (EmptyResultDataAccessException e) {
            return Dashboard.createWithOnlyId(dashboardId);
        }
    }

    @Override
    public Optional<Dashboard> findDashboardById(UUID dashboardId) {
        try {
            List <Roadmap> roadmaps = roadmapDAO.findAllByDashboardId(dashboardId);
            roadmaps.forEach(roadmapDAO::refreshRoadmap);

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
    public Optional<Dashboard> findDashboardByUserId(UUID userId) {
        try {
            UUID dashboardId = jdbcTemplate.queryForObject(selectDashboardByUserIdQuery, this::mapperDashboardFromRs,
                    userId).getDashboardId();

            List<Roadmap> roadmaps = roadmapDAO.findAllByDashboardId(dashboardId);
            roadmaps.forEach(roadmap -> {roadmapDAO.refreshRoadmap(roadmap);});

            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByUserIdQuery, this::mapperDashboardFromRs,
                    userId);

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

    @Override
    public Boolean dashboardExists(UUID dashboardId) {
        return jdbcTemplate.queryForObject(existsDashboardByIdQuery, Boolean.class, dashboardId);
    }

    public Dashboard mapperDashboardFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID dashboardId = (UUID) rs.getObject("id");
        int totalRoadmaps = rs.getInt("total_roadmaps");
        int totalCommits = rs.getInt("total_commits");
        int totalTasks = rs.getInt("total_tasks");
        Long totalTime = rs.getLong("total_time");
        UUID userId = (UUID) rs.getObject("user_id");
        return Dashboard.createWithAllFields(dashboardId, totalRoadmaps, totalCommits,
                totalTasks, totalTime, userId);
    }

}
