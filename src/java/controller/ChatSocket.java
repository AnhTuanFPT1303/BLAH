package controller; 

import java.io.IOException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.PathParam;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import dao.MessageDAO;
import java.sql.SQLException;


@ServerEndpoint("/ChatServlet/{userId}")
public class ChatSocket {
        private static Map<Integer, Session> userSessions = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") int userId) {
        userSessions.put(userId, session);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") int userId) throws Exception {
        // Handle incoming message
        JsonObject jsonMessage = Json.createReader(new StringReader(message)).readObject();
        int fromUserId = jsonMessage.getInt("fromUserId");
        int toUserId = jsonMessage.getInt("toUserId");
        String text = jsonMessage.getString("text");

        try {
            MessageDAO messageDAO = new MessageDAO();
            messageDAO.sendMessage(fromUserId, toUserId, text);
            
            Session toSession = userSessions.get(toUserId);
            if (toSession != null && toSession.isOpen()) {
                JsonObject jsonToSend = Json.createObjectBuilder()
                        .add("fromUserId", fromUserId)
                        .add("text", text)
                        .build();
                toSession.getAsyncRemote().sendText(jsonToSend.toString());
            }
        } catch (IOException | SQLException e) {
            // Handle exceptions
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") int userId) {
        userSessions.remove(userId);
    }
}

