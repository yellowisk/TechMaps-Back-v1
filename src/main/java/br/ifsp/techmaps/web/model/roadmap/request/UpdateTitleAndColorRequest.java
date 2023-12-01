package br.ifsp.techmaps.web.model.roadmap.request;

public class UpdateTitleAndColorRequest {
    private String title;
    private int color;

    public UpdateTitleAndColorRequest(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
