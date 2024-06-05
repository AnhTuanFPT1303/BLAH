/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.sqlConnect;
import java.sql.*;
import model.User;


/**
 *
 * @author HELLO
 */
public class userDAO {

    public boolean login(String email, String password) {
        boolean result = false;
        try {
            Connection conn = sqlConnect.getInstance().javaconnectsql();
            PreparedStatement st = conn.prepareStatement("Select * from userAccount where email=? AND password=?");
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getString("email").equals(email) && rs.getString("password").equals(password)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
        return result;
    }

    public String register(User user) {
        try {
            Connection conn = sqlConnect.getInstance().javaconnectsql();
            PreparedStatement st = conn.prepareStatement("INSERT INTO userAccount VALUES (?, ?, ?, ?)");
            st.setString(1, user.getFirst_name());
            st.setString(2, user.getLast_name());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
            st.execute();
            return "Registration Successful.";
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "Email alreay used.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Registration Failed.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknow Exception";
        }
    }

}
