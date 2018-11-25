package com.statusstock.studyzone;

public class News {

    private String title;
    private String url;
    private String image;
    private String sports;
    private String time;
    private String target;
    private String color;
    private int cc;


    public News() {

    }

    public News(String title, String url, String image, String sports, String time, String target, String color, int cc) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.sports = sports;
        this.time = time;
        this.target = target;
        this.color = color;
        this.cc = cc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }
}
