package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.post;
import util.sqlConnect;

public class PostDAO {

    public List<post> getPostsByUser(int userId) throws Exception {
        List<post> posts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = sqlConnect.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM post WHERE user_id =?");
            st.setInt(1, userId);
            rs = st.executeQuery();
            while (rs.next()) {
                post post = new post(rs.getInt("post_id"), userId, rs.getString("body"), rs.getTimestamp("post_time"), rs.getString("title"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public void createPost(String title, String body, int userId) throws Exception {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = sqlConnect.getInstance().getConnection();
            conn.setAutoCommit(false); // Disable auto-commit

            // Check if the user exists before inserting the post
            PreparedStatement userSt = conn.prepareStatement("SELECT 1 FROM userAccount WHERE user_id =?");
            userSt.setInt(1, userId + 1);
            ResultSet userRs = userSt.executeQuery();
            if (!userRs.next()) {
                throw new Exception("User with ID " + userId + " does not exist");
            }

            st = conn.prepareStatement("INSERT INTO post (user_id, title, body, post_time) VALUES (?,?,?,?)");
            st.setInt(1, userId);
            st.setString(2, title);
            st.setString(3, body);
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); // Commit the transaction
                System.out.println("Post created successfully!");
            } else {
                conn.rollback(); // Rollback the transaction
                throw new Exception("Failed to create post.");
            }
        } catch (SQLException e) {
            conn.rollback(); // Rollback the transaction
            throw new Exception("Error creating post: " + e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
