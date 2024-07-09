<%-- 
    Document   : chat
    Created on : Jul 9, 2024, 1:22:26 AM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WebSocket Chat</title>
        <script>
            let socket;

            function connect() {
                socket = new WebSocket("ws://localhost:80/blahproject/ChatServlet/" + ${sessionScope.user['user_id']});

                socket.onopen = function (event) {
                    console.log("WebSocket connected." +${sessionScope.user['user_id']});
                };

                socket.onmessage = function (event) {
                    console.log("Message from server: " + event.data);
                };

                socket.onclose = function (event) {
                    console.log("WebSocket closed.");
                };
            }

            function sendMessage() {
                let message = {
                    fromUserId: ${user_id}, // Replace with current user's ID
                    toUserId: toUserId,
                    text: messageText
                };
            }
        </script>
    </head>
    <body>
        <h1>WebSocket Chat</h1>
        <button onclick="connect()">Connect WebSocket</button>
        <input type="text" id="messageInput" placeholder="Type your message...">
        <button onclick="sendMessage()">Send</button>
    </body>
</html>
