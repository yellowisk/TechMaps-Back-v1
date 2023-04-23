package br.ifsp.techmaps;

import br.ifsp.techmaps.domain.entities.roadmap.Frontend;
import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Frontend roadmap1 = new Frontend(UUID.randomUUID(), "Frontend", RoadmapStatus.UNDONE, LocalDateTime.now(), RoadmapLanguage.JAVASCRIPT);
        roadmap1.createStage(StageEnum.LEARN_VSCODE, StageStatus.UNDONE, LocalDateTime.now());
        roadmap1.createStage(StageEnum.LEARN_INTELLIJ, StageStatus.UNDONE, LocalDateTime.now());
        roadmap1.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
        roadmap1.createStage(StageEnum.LEARN_CSS, StageStatus.UNDONE, LocalDateTime.now());
        Roadmap roadmap2 = Frontend.createFrontend("Frontend", RoadmapStatus.UNDONE, LocalDateTime.now(), RoadmapLanguage.JAVA);
        roadmap2.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
        roadmap2.createStage(StageEnum.LEARN_CSS, StageStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmap1.getStages());
        System.out.println(roadmap1);
        System.out.println(roadmap2);
    }

}
