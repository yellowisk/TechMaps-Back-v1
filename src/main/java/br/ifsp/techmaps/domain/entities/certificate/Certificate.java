package br.ifsp.techmaps.domain.entities.certificate;

import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Certificate {
    private String username;
    private String github;
    private String title;
    private RoadmapType type;
    private RoadmapLanguage language;
    private Timestamp startTime;
    private Timestamp finishTime;
    private Long totalTime;
    private Integer commits;
    private Integer stages;
    private Integer tasks;

    public Certificate(String username, String title, RoadmapType type,
                      RoadmapLanguage language, Timestamp startTime, Timestamp finishTime,
                      Long totalTime, Integer commits, Integer stages, Integer tasks) {
        this.username = username;
        this.title = title;
        this.type = type;
        this.language = language;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalTime = totalTime;
        this.commits = commits;
        this.stages = stages;
        this.tasks = tasks;
    }

    public Certificate() {
    }

    public static Certificate create(String username, String title, RoadmapType type,
                      RoadmapLanguage language, Timestamp startTime, Timestamp finishTime,
                      Long totalTime, Integer commits, Integer stages, Integer tasks) throws IOException {

        String baseFileName = "./src/main/resources/certificates/"+type+"-Roadmap-"+username+"'s-certificate_"+title+".pdf";
        String fileName = baseFileName;

        int suffix = 1;
        while (fileExists(fileName)) {
            fileName = baseFileName.replace(".pdf", "(" + suffix + ").pdf");
            suffix++;
        }

        PdfWriter writer = new PdfWriter(fileName);
        PdfFont font = PdfFontFactory.createFont("./src/main/resources/static/fonts/Righteous-Regular.ttf", PdfEncodings.IDENTITY_H);
        String techMapsPath = "./src/main/resources/static/images/logo-white-no-background.png";
        String commitImage = "./src/main/resources/static/images/branch_tree.png";
        String stagesImage = "./src/main/resources/static/images/stages.png";
        String githubImage = "./src/main/resources/static/images/github.png";

        PdfDocument pdf = new PdfDocument(writer);
        PageSize pageSize = new PageSize(800, 400);
        pdf.setDefaultPageSize(pageSize);

        Color whiteColor = new DeviceRgb(68, 153, 74);
        Color blackColor = new DeviceRgb(30, 30, 30);

        PdfCanvas pdfCanvas = new PdfCanvas(pdf.addNewPage());
        pdfCanvas.setColor(blackColor, true);
        pdfCanvas.rectangle(0, 0, pdf.getDefaultPageSize().getWidth(), pdf.getDefaultPageSize().getHeight());
        pdfCanvas.fill();
        Image imgTechMaps = new Image(ImageDataFactory.create(techMapsPath));
        imgTechMaps.setWidth(UnitValue.createPointValue(62));
        imgTechMaps.setHeight((62f));
        imgTechMaps.setFixedPosition(1, 720, 30);

        Image imgCommit = new Image(ImageDataFactory.create(commitImage));
        imgCommit.setWidth(UnitValue.createPointValue(62));
        imgCommit.setHeight((62f));
        imgCommit.setFixedPosition(1, 220, 30);

        Image imgStages = new Image(ImageDataFactory.create(stagesImage));
        imgStages.setWidth(UnitValue.createPointValue(62));
        imgStages.setHeight((62f));
        imgStages.setFixedPosition(1, 400, 30);

        Image imgGitHub = new Image(ImageDataFactory.create(githubImage));
        imgGitHub.setWidth(UnitValue.createPointValue(82));
        imgGitHub.setHeight((82f));
        imgGitHub.setFixedPosition(1, 550, 30);

        Document document = new Document(pdf);
        document.setFont(font);
        document.setFontColor(whiteColor);

        document.add(new Paragraph("CERTIFICATE").setFontSize(50f).setTextAlignment(TextAlignment.CENTER).setMarginTop(-60).setMarginBottom(-50));
        document.add(new Paragraph("OF ACHIEVEMENT").setFontSize(36f).setTextAlignment(TextAlignment.CENTER).setMarginBottom(-20));
        document.add(new Paragraph("The TechMaps team certifies that").setFontSize(10f).setTextAlignment(TextAlignment.CENTER).setMarginBottom(10));

        document.add(new Paragraph(username).setFontSize(40f).setTextAlignment(TextAlignment.CENTER).setMarginBottom(-10));
        SolidLine solidLine = new SolidLine(1f);
        solidLine.setColor(whiteColor);
        document.add(new LineSeparator(solidLine).setMarginLeft(80).setMarginRight(80));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Paragraph description = new Paragraph("Has dedicated, from " + sdf.format(startTime) +
                " to " + sdf.format(finishTime) + ", " + formatTotalTime(totalTime) + " of study on a " +
                type.toString() + " learning Roadmap, acquiring " +
                "valuable skills on the programming language " + language + " and another related " +
                "fields of knowledge.").setMarginLeft(100).setMarginRight(100).setTextAlignment(TextAlignment.CENTER);

        document.add(description.setFontSize(15f));

        float twoColLeft = 185f;
        float twoColRight = twoColLeft + 150f;

        Table nestedTable = new Table(new float[]{twoColRight/4, twoColRight/4, twoColRight/4, twoColRight/4, twoColRight/4, twoColRight/4});
        nestedTable.addCell(new Cell().add(new Cell().add(commits.toString()).setFontSize(20f)).setBorder(Border.NO_BORDER)).setMarginBottom(-30).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph(stages.toString()).setFontSize(20f)).setBorder(Border.NO_BORDER)).setMarginBottom(-30).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Cell().add("100%").setFontSize(20f)).setBorder(Border.NO_BORDER)).setMarginBottom(-30).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Cell().add("COMMITS").setFontSize(10f)).setBorder(Border.NO_BORDER)).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph("STAGES").setFontSize(10f)).setBorder(Border.NO_BORDER)).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));
        nestedTable.addCell(new Cell().add(new Paragraph("BRIO").setFontSize(10f)).setBorder(Border.NO_BORDER)).setTextAlignment(TextAlignment.CENTER);
        nestedTable.addCell(new Cell().add(new Cell().add("")).setBorder(Border.NO_BORDER));

        document.add(nestedTable.setHorizontalAlignment(HorizontalAlignment.CENTER));

        document.add(imgTechMaps);
        document.add(imgCommit);
        document.add(imgStages);
        document.add(imgGitHub);

        document.close();

        return new Certificate(username, title, type, language, startTime, finishTime, totalTime, commits, stages, tasks);
    }

    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    private static String formatTotalTime(long totalTime) {
        long days = TimeUnit.MINUTES.toDays(totalTime);
        long hours = TimeUnit.MINUTES.toHours(totalTime % TimeUnit.DAYS.toMinutes(1));
        long minutes = totalTime % TimeUnit.HOURS.toMinutes(1);

        if (days > 365) {
            long years = days / 365;
            return years + (years == 1 ? " year" : " years");
        } else if (days > 30) {
            long months = days / 30;
            return months + (months == 1 ? " month" : " months");
        } else if (days > 0) {
            return days + (days == 1 ? " day" : " days");
        } else if (hours > 0) {
            return hours + (hours == 1 ? " hour" : " hours");
        } else {
            return minutes + (minutes == 1 ? " minute" : " minutes");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoadmapType getType() {
        return type;
    }

    public void setType(RoadmapType type) {
        this.type = type;
    }

    public RoadmapLanguage getLanguage() {
        return language;
    }

    public void setLanguage(RoadmapLanguage language) {
        this.language = language;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getCommits() {
        return commits;
    }

    public void setCommits(Integer commits) {
        this.commits = commits;
    }

    public Integer getStages() {
        return stages;
    }

    public void setStages(Integer stages) {
        this.stages = stages;
    }

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }
}
