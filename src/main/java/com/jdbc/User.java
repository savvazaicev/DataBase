package com.jdbc;

public class User {
    private int userid;
    private String name;
    private String sureName;

    public User(){}
    public User(int userid, String name, String sureName){
        this.userid = userid;
        this.name = name;
        this.sureName = sureName;
    }

    @Override
    public String toString(){
        return "User: [" + userid + "\t" + name + "\t" + sureName + "]";
    }
}
