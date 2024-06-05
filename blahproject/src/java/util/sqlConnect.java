package util;

import java.io.IOException;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HELLO
 */
public class sqlConnect {

    public static Connection javaconnectsqlserver() throws Exception {
        Connection connection = null;
        String userName = "sa";
        String passWord = "123";
        String port = "1433";
        String ip = "127.0.0.1";
        String dbName = "Class";
        String deviceName = "LAPTOP-5D2CNVK4";
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String sqlUrl = "jdbc:sqlserver://LAPTOP-5D2CNVK4;databaseName=Class;encrypt=false;trustServerCertificate=false";
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(sqlUrl, userName, passWord);
        } catch (SQLException s) {
            System.out.println("Connect Failed");
        }
        return connection;
    }
}
