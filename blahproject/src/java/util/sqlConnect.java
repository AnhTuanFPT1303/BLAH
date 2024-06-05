package util;

import dao.userDAO;
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

    private static sqlConnect instance = null;
    private Connection connection = null;
    private String userName = "sa";
    private String passWord = "123";
    private String port = "1433";
    private String ip = "127.0.0.1";
    private String dbName = "Blah";
    private String deviceName = "LAPTOP-5D2CNVK4";
    private String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String sqlUrl = "jdbc:sqlserver://LAPTOP-5D2CNVK4;databaseName=Blah;encrypt=false;trustServerCertificate=false";

    public Connection javaconnectsql() throws Exception {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(sqlUrl, userName, passWord);
        } catch (SQLException s) {
            System.out.println("Connect Failed");
        }
        return connection;
    }

    public static sqlConnect getInstance() throws SQLException {
        if (instance == null) {
            instance = new sqlConnect();
        } 
        return instance;
    }

    
    public static void main(String[] args) throws SQLException {
        userDAO userDao= new userDAO();
        boolean test =userDao.login("admin123@gmail.com", "admin");
        if(test) {
            System.out.println("Success");
        }
    }
}
