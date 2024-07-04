/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author bim26
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import util.sqlConnect;

public class postDAO {

    public void addPost(Post p) {
        String query = "INSERT INTO post (user_id, body, image_path, post_time  ) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = sqlConnect.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, p.getUser_id());
            stmt.setString(2, p.getBody());
            stmt.setString(3, p.getImage_path());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            ResultSet rs = null;
            Connection conn = null;
            Statement stmt = null;
            conn = sqlConnect.getInstance().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT p.post_id, p.body, p.post_time, p.user_id, p.image_path, u.first_name, u.last_name "
                    + "FROM post p JOIN userAccount u ON p.user_id = u.user_id "
                    + "ORDER BY p.post_time DESC");
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                int user_id = rs.getInt("user_id");
                String body = rs.getString("body");
                Timestamp post_time = rs.getTimestamp("post_time");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String image_path = rs.getString("image_path");
                Post post = new Post(post_id, user_id, body, post_time, first_name, last_name, image_path);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    
    
    public static List<Post> getMyPosts(int userId) {
        List<Post> posts = new ArrayList<>();
        try {
            ResultSet rs = null;
            Connection conn = null;
            PreparedStatement stmt = null;
            conn = sqlConnect.getInstance().getConnection();
            stmt = conn.prepareStatement("SELECT p.post_id, p.body, p.post_time, p.user_id, u.first_name, u.last_name "
                    + "FROM post p JOIN userAccount u ON p.user_id = u.user_id "
                    + "WHERE p.user_id =? "
                    + "ORDER BY p.post_time DESC");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                int user_id = rs.getInt("user_id");
                String body = rs.getString("body");
                Timestamp post_time = rs.getTimestamp("post_time");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Post post = new Post(post_id, user_id, body, post_time, first_name, last_name);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

}
