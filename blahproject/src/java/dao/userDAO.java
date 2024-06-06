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
            Connection conn = sqlConnect.getInstance().getConnection();
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
            Connection conn = sqlConnect.getInstance().getConnection();
            boolean result = checkDuplicateEmail(user.getEmail());
            if (result) {
                return "Duplicated Email";
            } else {
                conn.close();
                conn = sqlConnect.getInstance().getConnection();
                PreparedStatement st = conn.prepareStatement("INSERT INTO userAccount (first_name, last_name, password, email) VALUES (?, ?, ?, ?)");
                st.setString(1, user.getFirst_name());
                st.setString(2, user.getLast_name());
                st.setString(3, user.getPassword());
                st.setString(4, user.getEmail());
                st.executeUpdate();
                return "Registration Successful.";
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 5000) {
                return "Email Error.";
            } else {
                return "SQL Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown Exception";
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        User u = new User();
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                u.setUser_id(rs.getInt("user_id"));
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            System.out.println("Action Failed");
        }
        return u;
    }

   private boolean checkDuplicateEmail(String email) throws Exception {
    boolean isDuplicate = false;
    try (Connection connection = sqlConnect.getInstance().getConnection();
         CallableStatement stmt = connection.prepareCall("{call checkDuplicateEmail(?)}")) {

        stmt.setString(1, email);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                if(rs.getString("Message").equals("10000"))
                isDuplicate = true;
            }
        }
    } catch (SQLException e) {
        if (e.getErrorCode() == 50000) {
            isDuplicate = true;
        } else {
            e.printStackTrace();
        }
    }
    return isDuplicate;
}

}
