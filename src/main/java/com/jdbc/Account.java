package com.jdbc;

public class Account { //не используется
    private int accountid;
    private int account;
    private int userid;

    public Account(){}
    public Account(int accountid, int account, int userid){
        this.accountid = accountid;
        this.account = account;
        this.userid = userid;
    }

    @Override
    public String toString(){
        return "User: [" + accountid + "\t" + account + "\t" + userid + "]";
    }
}
