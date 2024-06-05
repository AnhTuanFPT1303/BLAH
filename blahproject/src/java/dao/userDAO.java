/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.sqlConnect;
import java.sql.*;

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
}
