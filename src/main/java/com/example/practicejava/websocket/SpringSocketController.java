package com.example.practicejava.websocket;

import org.springframework.web.socket.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpringSocketController implements WebSocketHandler {

    // 用于存储所有连接的session
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // 建立连接后
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("连接成功");
        sessions.add(session);
    }

    // 收到消息后
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到消息：" + message.getPayload());
        System.out.println(session);
        // 判断消息类型
        if (message.getPayloadLength() > 0) {
            // 发送消息给所有连接的客户端
            System.out.println(sessions.size());
            for (WebSocketSession s : sessions) {
                if (s.isOpen()){
                    s.sendMessage(new TextMessage("服务器收到消息：" + message.getPayload()));
                }
            }
        }
    }

    // 传输错误后
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("传输错误: " + exception.getMessage());
    }

    // 关闭连接后
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("连接关闭");
        session.close();
    }

    // 是否支持部分消息
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}



