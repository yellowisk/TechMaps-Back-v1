package br.ifsp.techmaps.usecases.dashboard.gateway;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;

import java.util.*;

public interface DashboardDAO {

    Dashboard saveNewDashboard(Dashboard dashboard);

    Optional<Dashboard> findDashboardById(UUID dashboardId);

}
