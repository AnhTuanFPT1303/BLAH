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

    public boolean sendFriendRequest(int userRequest, int userAccept) {
        try {
            Connection conn = sqlConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO friendship VALUES (?, ?, 'pending')");
            st.setInt(1, userRequest);
            st.setInt(2, userAccept);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("An unknown error occurred while sending friend request: " + e.getMessage());
            return false;
        }
        return true;
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

    public ArrayList<String> getAllFriendRequest(int userId) throws Exception {
        ArrayList<String> requested_userId_List = new ArrayList<>();
        Connection conn = sqlConnect.getInstance().getConnection();
        PreparedStatement st = conn.prepareStatement("SELECT u.user_id, u.first_name, u.last_name FROM friendship f JOIN userAccount u ON f.user_request = u.user_id WHERE f.user_accept = ? AND f.status = 'pending'");
        st.setInt(1, userId); //userid in session
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int userRequest = rs.getInt(1);
            String first_name = rs.getString(2);
            String last_name = rs.getString(3);
            requested_userId_List.add(userRequest+"."+first_name+"."+last_name);
        }
        return requested_userId_List;
    }

    public boolean isFriendRequestSent(int userRequest, int userAccept) throws Exception {
        Connection conn = sqlConnect.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT status FROM friendship WHERE user_request = ? AND user_accept = ?");
        ps.setInt(1, userRequest);
        ps.setInt(2, userAccept);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return true; // Friend request already sent
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
