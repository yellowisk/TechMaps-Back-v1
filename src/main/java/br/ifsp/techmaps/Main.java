package br.ifsp.techmaps;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.stage.StageEnum;
import br.ifsp.techmaps.domain.entities.stage.StageStatus;
import br.ifsp.techmaps.domain.entities.user.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        User user = new User("João", "email", "password", null);

        System.out.println("*-*-* Standard Frontend roadmap *-*-* \n");
        Roadmap roadmap1 = Roadmap.createFrontend("Frontend", user, RoadmapStatus.UNDONE, RoadmapLanguage.JAVASCRIPT, LocalDateTime.now());
            roadmap1.createStage(StageEnum.LEARN_VSCODE, StageStatus.UNDONE, LocalDateTime.now());
            roadmap1.createStage(StageEnum.LEARN_INTELLIJ, StageStatus.UNDONE, LocalDateTime.now());
            roadmap1.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
            roadmap1.createStage(StageEnum.LEARN_CSS, StageStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmap1);
        System.out.println(roadmap1.getStages() + "\n\n\n");

        System.out.println("*-*-* Frontend roadmap with incorrect language *-*-* \n");
        Roadmap roadmap2 = Roadmap.createFrontend("Frontend", user, RoadmapStatus.UNDONE, RoadmapLanguage.JAVA, LocalDateTime.now());
        roadmap2.createStage(StageEnum.LEARN_VSCODE, StageStatus.UNDONE, LocalDateTime.now());
        roadmap2.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
        roadmap2.createStage(StageEnum.LEARN_CSS, StageStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmap2);
        System.out.println(roadmap2.getStages() + "\n\n\n");

        System.out.println("*-*-* Standard Backend roadmap *-*-* \n");
        Roadmap roadmap3 = Roadmap.createBackEnd("Backend", user, RoadmapStatus.UNDONE, RoadmapLanguage.PYTHON, LocalDateTime.now());
            roadmap3.createStage(StageEnum.LEARN_VSCODE, StageStatus.UNDONE, LocalDateTime.now());
            roadmap3.createStage(StageEnum.LEARN_INTELLIJ, StageStatus.UNDONE, LocalDateTime.now());
            roadmap3.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
            roadmap3.createStage(StageEnum.LEARN_JS, StageStatus.UNDONE, LocalDateTime.now());
            roadmap3.createStage(StageEnum.LEARN_SPRING, StageStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmap3);
        System.out.println(roadmap3.getStages() + "\n\n\n");

        System.out.println("*-*-* Backend roadmap with incorrect language *-*-* \n");
        Roadmap roadmap4 = Roadmap.createBackEnd("Backend", user, RoadmapStatus.UNDONE, RoadmapLanguage.JAVASCRIPT, LocalDateTime.now());
        roadmap4.createStage(StageEnum.LEARN_INTELLIJ, StageStatus.UNDONE, LocalDateTime.now());
        roadmap4.createStage(StageEnum.LEARN_JAVA, StageStatus.UNDONE, LocalDateTime.now());
        roadmap4.createStage(StageEnum.LEARN_JS, StageStatus.UNDONE, LocalDateTime.now());
        roadmap4.createStage(StageEnum.LEARN_SPRING, StageStatus.UNDONE, LocalDateTime.now());
        System.out.println(roadmap4);
        System.out.println(roadmap4.getStages() + "\n\n\n");

        System.out.println(user.getRoadmaps());
    }

}
