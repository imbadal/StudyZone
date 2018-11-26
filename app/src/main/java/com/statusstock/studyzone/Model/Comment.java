package com.statusstock.studyzone.Model;

public class Comment {

    private String comment;
    private String uid;
    private String date;
    private String time;
    private String username;


    public Comment() {

    }

    public Comment(String comment, String uid, String date, String time, String username) {
        this.comment = comment;
        this.uid = uid;
        this.date = date;
        this.time = time;
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
