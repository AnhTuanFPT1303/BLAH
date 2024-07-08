/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import util.sqlConnect;
import java.sql.*;
import model.User;
import java.util.ArrayList;

/**
 *
 * @author HELLO
 */
public class FriendDAO {

    public void sendFriendRequest(int userRequest, int userAccept) {
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO friendship VALUES (?, ?, 'pending')");
            st.setInt(1, userRequest);
            st.setInt(2, userAccept);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while sending friend request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unknown error occurred while sending friend request: " + e.getMessage());
        }
    }

    public void acceptFriendRequest(int userRequest, int userAccept) {
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("UPDATE friendship SET status = 'accepted' WHERE user_request = ? AND user_accept = ?");
            st.setInt(1, userRequest);
            st.setInt(2, userAccept);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while accept friend request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unknown error occurred while accept friend request: " + e.getMessage());
        }
    }

    public void rejectFriendRequest(int userRequest, int userAccept) {
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("UPDATE friendship SET status = 'rejected' WHERE user_request = ? AND user_accept = ?");
            st.setInt(1, userRequest);
            st.setInt(2, userAccept);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while reject friend request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unknown error occurred while reject friend request: " + e.getMessage());
        }
    }

    public ArrayList<Integer> getAllFriendRequest(int userId) throws Exception {
        ArrayList<Integer> requested_userId_List = new ArrayList<>();
        Connection conn = sqlConnect.getInstance().getConnection();
        PreparedStatement st = conn.prepareStatement("SELECT user_request FROM friendship WHERE user_accept = ? AND status = 'pending'");
        st.setInt(1, userId); //userid in session
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int userRequest = rs.getInt("user_request");
            requested_userId_List.add(userRequest);
        }
        return requested_userId_List;
    }

}
