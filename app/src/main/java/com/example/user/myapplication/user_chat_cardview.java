package com.example.user.myapplication;

public class user_chat_cardview {
    private String username;
    private String salonname;
    private String umsg;
    private String replay;
    private String time;

    public user_chat_cardview(String username, String salonname, String umsg, String replay, String time) {
        this.username = username;
        this.salonname = salonname;
        this.umsg = umsg;
        this.replay = replay;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getSalonname() {
        return salonname;
    }

    public String getUmsg() {
        return umsg;
    }

    public String getReplay() {
        return replay;
    }

    public String getTime() {
        return time;
    }
}
