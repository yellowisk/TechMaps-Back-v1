package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

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

    @Override
    public Dashboard saveNewDashboard(Dashboard dashboard) {
        return null;
    }

    @Override
    public Optional<Dashboard> findDashboardById(UUID dashboardId) {
        try {
            Dashboard dashboard = jdbcTemplate.queryForObject(selectDashboardByIdQuery, (rs, rowNum) -> {
                int total_time = rs.getInt("total_time");
                return Dashboard.createWithOnlyIdAndTime(dashboardId, total_time);
            }, dashboardId);

            if (Objects.isNull(dashboard)) {
                throw new IllegalStateException("Dashboard not found");
            }
            return Optional.of(dashboard);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
