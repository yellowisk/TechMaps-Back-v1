package br.ifsp.techmaps.usecases.roadmap;

import br.ifsp.techmaps.domain.entities.dashboard.Dashboard;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.usecases.dashboard.gateway.DashboardDAO;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.web.model.roadmap.request.CreateRoadmapRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadmapCRUDImpl implements RoadmapCRUD {

    private final RoadmapDAO roadmapDAO;
    private final DashboardDAO dashboardDAO;

    public RoadmapCRUDImpl(RoadmapDAO roadmapDAO, DashboardDAO dashboardDAO) {
        this.roadmapDAO = roadmapDAO;
        this.dashboardDAO = dashboardDAO;
    }

    @Override
    public Roadmap addNewRoadmap(UUID dashboardId, CreateRoadmapRequest request) {
        Optional<Dashboard> optionalDashboard = dashboardDAO.findDashboardById(dashboardId);

        Roadmap roadmap = Roadmap.createWithoutId(request.getTitle(), request.getType(), RoadmapStatus.UNCOMPLETE,
                request.getRoadmapLanguage(), LocalDateTime.now(), null, 0,
                dashboardId);

        if (roadmap.getRoadmapLanguage().equals(RoadmapLanguage.JAVA)) {
            List<Stage> stages = new ArrayList<>(10);
            Stage stage = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, StageEnum.LEARN_JS, StageStatus.UNDONE, 0);
            for (int i = 0; i < 9; i++) {
                Stage stageFront = Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null, StageStatus.UNDONE, 0);

                for (StageEnum condition : StageEnum.values()) {
                    stage.setTheme(condition);
                }
                stages.add(Stage.createStageWithoutTasks(UUID.randomUUID(), roadmap, null, StageStatus.UNDONE, 0));

            }
        }

        return roadmapDAO.saveRoadmap(roadmap);
    }
}
