package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
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

    private final RoadmapDAO roadmapDAO;
    private final JsonUtil jsonUtil;

    public DashboardDAOImpl(JdbcTemplate jdbcTemplate, RoadmapDAO roadmapDAO, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.roadmapDAO = roadmapDAO;
        this.jsonUtil = jsonUtil;
    }

    @Value("${queries.sql.dashboard-dao.insert.dashboard}")
    private String insertDashboardQuery;

    @Value("${queries.sql.dashboard-dao.select.dashboard-by-id}")
    private String selectDashboardByIdQuery;

    @Override
    public Dashboard saveNewDashboard(Dashboard dashboard) {
        return null;
    }

    @Override
    public Optional<Dashboard> findDashboardById(UUID dashboardId) {
        try {
            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByIdQuery, this::mapperDashboardFromRs,
                    dashboardId);

            if (Objects.isNull(dashboard)) {
                throw new IllegalStateException("Dashboard not found");
            }

//            List<Roadmap> completedRoadmaps = roadmapDAO.findAllCompletedByDashboardId(dashboardId);
//            for (Roadmap roadmap : completedRoadmaps) {
//                    dashboard.setTotalRoadmaps(completedRoadmaps.size());
//            }

            return Optional.of(dashboard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Dashboard mapperDashboardFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID dashboardId = (UUID) rs.getObject("id");
        int totalRoadmaps = rs.getInt("total_roadmaps");
        int totalTasks = rs.getInt("total_tasks");
        int totalCommits = rs.getInt("total_commits");
        Timestamp totalTime = (Timestamp) rs.getTimestamp("total_time");
        return Dashboard.createWithAllFields(dashboardId, totalRoadmaps, totalTasks,
                totalCommits, totalTime);
    }
}
