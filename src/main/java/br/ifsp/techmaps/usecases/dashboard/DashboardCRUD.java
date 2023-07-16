package br.ifsp.techmaps.usecases.dashboard;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import java.util.*;

public interface DashboardCRUD {

    Dashboard saveNewDashboard(Dashboard dashboard);

    Dashboard getDashboardById (UUID dashboardId);

}
