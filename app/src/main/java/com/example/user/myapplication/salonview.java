package com.example.user.myapplication;

public class salonview {
    private String names;
    private String address;
    String info;


    public salonview(String names, String address, String info) {
        this.names = names;
        this.address = address;
        this.info = info;
    }

    public String getNames() {
        return names;
    }

    public String getAddress() {
        return address;
    }

    public String getInfo() {
        return info;
    }
}
