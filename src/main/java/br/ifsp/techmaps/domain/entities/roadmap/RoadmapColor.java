package br.ifsp.techmaps.domain.entities.roadmap;

public enum RoadmapColor {

    RED(0),
    ORANGE(1),
    BROWN(3),
    YELLOW(4),
    GREEN(5),
    BLUE(6),
    PURPLE(7),
    PINK(8),
    BLACK(9),
    WHITE(10),
    GRAY(11);

    private int colorCode;

    RoadmapColor(int colorCode) {
        this.colorCode = colorCode;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}
