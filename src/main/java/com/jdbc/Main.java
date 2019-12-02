package com.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";

    public static void main(String[] args) throws SQLException {
        System.out.println("Таблица user: ");
        userOutput();

        System.out.println("Данные пользователя по id: ");
        int id = 8;
        User user = userDataById(id);
        System.out.println(user);
        System.out.println();

        System.out.println("Таблица account: ");
        ArrayList<Account> accountList = accountData();
        for (int i = 0; i < accountList.size(); i++) {
            System.out.println(accountList.get(i));
        }
        System.out.println();

        System.out.println("Номера всех аккаунтов: ");
        ArrayList<Integer> allAccounts = new ArrayList<Integer>();
        allAccounts = allAccounts();
        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println(allAccounts.get(i));
        }
    }

    private static void userOutput() throws SQLException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bankdb.user";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);

        while (resSet.next()) {
            int userid;
            String name;
            String sureName;
            userid = resSet.getInt("userid");
            name = resSet.getString("name");
            sureName = resSet.getString("sureName");
            User user = new User(userid, name, sureName);
            System.out.println(user);
        }
        System.out.println();
        statement.close();
        conn.close();
    }

    public static User userDataById(int userid) throws SQLException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bankdb.user where userid = " + userid;
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);
        User user = null;

        while (resSet.next()) {
            String name;
            String sureName;
            name = resSet.getString("name");
            sureName = resSet.getString("sureName");
            user = new User(userid, name, sureName);
        }
        statement.close();
        conn.close();
        return user;
    }

    private static ArrayList<Account> accountData() throws SQLException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bankdb.account";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);
        ArrayList<Account> accountList = new ArrayList<>();

        while (resSet.next()) {
            int accountid;
            int account;
            int userid;
            accountid = resSet.getInt("accountid");
            account = resSet.getInt("account");
            userid = resSet.getInt("userid");
            Account newAccount = new Account(accountid, account, userid);
            accountList.add(newAccount);
        }
        statement.close();
        conn.close();
        return accountList;
    }

    public static ArrayList<Integer> allAccounts() throws SQLException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "select * from bankdb.account";
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);
        ArrayList<Integer> allAccounts = new ArrayList<Integer>();

        while (resSet.next()) {
            int account;
            account = resSet.getInt("account");
            allAccounts.add(account);
        }

        statement.close();
        conn.close();
        return allAccounts;
    }
}
