package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Message;
import util.sqlConnect;

public class MessageDAO {

    public MessageDAO() {
    }

    // Existing method to fetch messages
//    public List<Message> getMessages(int fromUserId, int toUserId) throws SQLException {
//        // Implementation details as previously discussed
//    }

    // New method to insert messages
    public boolean sendMessage(int fromUserId, int toUserId, String message) throws SQLException, Exception {
        String query = "INSERT INTO message (from_user, to_user, message) VALUES (?, ?, ?)";
        Connection conn = sqlConnect.getInstance().getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, fromUserId);
            stmt.setInt(2, toUserId);
            stmt.setString(3, message);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
