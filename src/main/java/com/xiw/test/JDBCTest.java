package com.xiw.test;

import org.junit.Test;

import java.sql.*;
import java.util.Enumeration;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement().toString());
        }
        System.out.println("....");
        DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/abc-main?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "123456");
        System.out.println("get mysql connection");
        DriverManager.getConnection(
                "jdbc:dm://192.168.123.9:5236/STJ?nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true&schema=STJ",
                "SYSDBA",
                "SYSDBA");
        System.out.println("get dm connection");

    }

    @Test
    public void testMysqlConn() throws SQLException {
        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc-main?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true", "root", "123456");
        // 创建 Statement
        Statement statement = connection.createStatement();
        // 执行查询语句
        ResultSet resultSet = statement.executeQuery("SELECT * FROM cu_calculator_log limit 10");
        // 处理查询结果
        while (resultSet.next()) {
            System.out.println(resultSet);
        }
        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void testDMConn() throws SQLException {
        // 获取连接
        Connection connection = DriverManager.getConnection(
                "jdbc:dm://192.168.123.9:5236/STJ?nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true&schema=STJ",
                "SYSDBA",
                "SYSDBA");
        // 创建 Statement
        Statement statement = connection.createStatement();
        // 执行查询语句
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sys_audit_log limit 10");
        // 处理查询结果
        while (resultSet.next()) {
            System.out.println(resultSet);
        }
        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
