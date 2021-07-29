package com.example.user.myapplication;

public class rates_cardsview {
    private String names;
    private String des;
    String rate;
    String msg;

    public rates_cardsview(String names, String des, String rate, String msg) {
        this.names = names;
        this.des = des;
        this.rate = rate;
        this.msg = msg;
    }

    public String getNames() {
        return names;
    }

    public String getDes() {
        return des;
    }

    public String getRate() {
        return rate;
    }

    public String getMsg() {
        return msg;
    }
}
