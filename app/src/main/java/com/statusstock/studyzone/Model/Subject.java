package com.statusstock.studyzone.Model;

public class Subject {

    private String num;
    private String qsn;
    private String ans;


    public Subject() {

    }

    public Subject(String num, String qsn, String ans) {
        this.num = num;
        this.qsn = qsn;
        this.ans = ans;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getQsn() {
        return qsn;
    }

    public void setQsn(String qsn) {
        this.qsn = qsn;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
