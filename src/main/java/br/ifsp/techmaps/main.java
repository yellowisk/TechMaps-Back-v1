package br.ifsp.techmaps;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.Stage;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.interfaces.roles.BackEnd;
import br.ifsp.techmaps.domain.interfaces.roles.FrontEnd;

import java.time.LocalDateTime;
import java.util.UUID;

public class main {
    public static void main(String[] args) {
        BackEnd roadmapOne = new Roadmap(UUID.randomUUID(),"title", RoadmapRole.BACKEND, RoadmapLanguage.JAVA, RoadmapStatus.UNDONE, LocalDateTime.now());
        BackEnd backendRoadmapOne = (BackEnd) roadmapOne;
        System.out.println(roadmapOne);
        FrontEnd roadmapTwo = new Roadmap("title", RoadmapRole.FRONTEND, RoadmapLanguage.JAVASCRIPT, RoadmapStatus.UNDONE, LocalDateTime.now());
        Stage stage = new Stage(roadmapOne, StageEnum.LEARN_CSS, StageStatus.UNDONE);
        System.out.println(stage);
        Stage stage2 = new Stage(roadmapOne, StageEnum.LEARN_HTML, StageStatus.UNDONE);
        System.out.println(stage2);
    }

}
