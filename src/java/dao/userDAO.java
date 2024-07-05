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
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
        return result;
    }

    public String register(User user) {
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            CallableStatement st = conn.prepareCall("INSERT INTO userAccount Values (?,?,?,?,?)"); //Call register procedure in SQL Server
            st.setString(1, user.getFirst_name());
            st.setString(2, user.getLast_name());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
            st.setString(5, user.getProfile_pic());
            st.execute();
            return "Registration Successful.";
        } catch (SQLException e) {
            return "Duplicate Email.";
        } catch (Exception e) {
            return "Unknown Exception";
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        User u = new User();
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM userAccount WHERE email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                u.setUser_id(rs.getInt(1));
                u.setFirst_name(rs.getString(2));
                u.setLast_name(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setProfile_pic(rs.getString(6));
            }

        } catch (Exception e) {
            System.out.println("Action Failed");
        }
        return u;
    }

    public boolean checkEmail(String email) {
        boolean exists = false;
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT 1 FROM userAccount WHERE email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while checking email.");
        } catch (Exception e) {
            System.out.println("An unknown error occurred while checking email.");
        }
        return exists;
    }

    public void updateUserName(int userId, String newFirstName, String newLastName) throws SQLException, Exception {
        String query = "UPDATE userAccount SET first_name = ?, last_name = ? WHERE user_id = ?";
        try (Connection conn = sqlConnect.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updatePassword(int userId, String newPassword) throws SQLException, Exception {
        String query = "UPDATE userAccount SET password = ? WHERE user_id = ?";
        try (Connection conn = sqlConnect.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteUser(int userId) throws SQLException, Exception {
        String query = "DELETE FROM userAccount WHERE user_id = ?";
        try (Connection conn = sqlConnect.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws SQLException {
        userDAO test = new userDAO();
        User user = test.getUserByEmail("nguyenhuuanhtuan123@gmail.com");
        String img = user.getProfile_pic();
        System.out.println(img);
    }
}
