package br.ifsp.techmaps;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapRole;
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
        System.out.println(roadmapOne.getClass());
        roadmapOne.internet(StageEnum.LEARN_INTERNET);

        FrontEnd roadmapTwo = new Roadmap(UUID.randomUUID(), "Front-end", RoadmapRole.FRONTEND, RoadmapLanguage.JAVASCRIPT, RoadmapStatus.DONE, LocalDateTime.now());
        System.out.println(roadmapTwo.getClass());


        Roadmap roadmapThree = new Roadmap(UUID.randomUUID(),"title", RoadmapRole.BACKEND, RoadmapLanguage.JAVA, RoadmapStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmapThree.getClass());
        System.out.println(((Roadmap) roadmapOne).getRoadmapLanguage());


    }

}
