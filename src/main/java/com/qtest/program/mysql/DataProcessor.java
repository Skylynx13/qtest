package com.qtest.program.mysql;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    List<QRow> queryQtable() throws ClassNotFoundException, SQLException {
        ArrayList<QRow> qRows = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.199.247:3306/tflibdb", "skylynx", "Sky123$%^");
        String sql = "Select * from qtable";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            QRow qRow = new QRow();
            qRow.id=resultSet.getInt(1);
            qRow.name=resultSet.getString(2);
            qRow.datetime=resultSet.getString(3);
            qRow.note=resultSet.getString(4);
            qRows.add(qRow);
        }
        System.out.println("===================In query");
        System.out.println(qRows.toString().replaceAll(", ", "\n"));
        statement.close();
        connection.close();
        return qRows;
    }

    List<QRow> queryQtableByStatement() throws ClassNotFoundException, SQLException {
        ArrayList<QRow> qRows = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.199.247:3306/tflibdb", "skylynx", "Sky123$%^");
        String sql = "Select * from qtable";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.toString());
            QRow qRow = new QRow();
            qRow.id=resultSet.getInt(1);
            qRow.name=resultSet.getString(2);
            qRow.datetime=resultSet.getString(3);
            qRow.note=resultSet.getString(4);
            qRows.add(qRow);
        }
        System.out.println("===================In query");
        System.out.println(qRows.toString().replaceAll(", ", "\n"));
        statement.close();
        connection.close();
        return qRows;
    }

    int insertQtable() throws ClassNotFoundException, SQLException {
        ArrayList<QRow> qRows = new ArrayList<>();
        QRow qRow = new QRow();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.199.247:3306/tflibdb", "skylynx", "Sky123$%^");
        String sql = "insert into qtable(id, name, datetime, note) values(?, ?, ?, ?)";

        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 3);
        statement.setString(2, "row3");
//        statement.setTimestamp(3, timestamp);
        statement.setObject(3, "2021-12-18 21:00:00.123");
        statement.setString(4, "note3");
        int ret = statement.executeUpdate();
        statement.close();
        connection.close();
        return ret;
    }

    public int deleteQtable() throws ClassNotFoundException, SQLException {
        ArrayList<QRow> qRows = new ArrayList<>();
        QRow qRow = new QRow();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.199.247:3306/tflibdb", "skylynx", "Sky123$%^");
        String sql = "delete from qtable where id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 3);
        int ret = statement.executeUpdate();
        statement.close();
        connection.close();
        return ret;
    }
}
