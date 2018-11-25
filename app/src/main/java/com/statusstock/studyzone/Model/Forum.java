package com.statusstock.studyzone.Model;

public class Forum {

    private String qsn;
    private String desc;
    private String date;
    private String time;
    private String ufulname;


    public Forum() {

    }

    public Forum(String qsn, String desc, String date, String time, String ufulname) {
        this.qsn = qsn;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.ufulname = ufulname;
    }

    public String getqsn() {
        return qsn;
    }

    public void setqsn(String qsn) {
        this.qsn = qsn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getUfulname() {
        return ufulname;
    }

    public void setUfulname(String ufulname) {
        this.ufulname = ufulname;
    }
}
