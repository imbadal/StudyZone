package com.statusstock.studyzone.Model;

public class SubjectDetails {

    private String chapter;
    private String url;


    public SubjectDetails() {

    }

    public SubjectDetails(String chapter, String url) {
        this.chapter = chapter;
        this.url = url;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
