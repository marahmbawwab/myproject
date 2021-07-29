package com.example.user.myapplication;

public class chat_cardview_salon {
    private String username;
    private String msg;
    private String time;

    public chat_cardview_salon(String username, String msg, String time) {

        this.username = username;
        this.msg = msg;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }
}
