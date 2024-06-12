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
            CallableStatement st = conn.prepareCall("{call registerUser(?,?,?,?)}"); //Call register procedure in SQL Server
            st.setString(1, user.getFirst_name());
            st.setString(2, user.getLast_name());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
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

    public static void main(String[] args) {
        User user = new User();
        user.setEmail("anht111uan11231232332@gmail.com");
        user.setFirst_name("Tuan");
        user.setLast_name("Tet");
        user.setPassword("123");
        userDAO userdao = new userDAO();
        System.out.println(userdao.register(user));
    }
}
