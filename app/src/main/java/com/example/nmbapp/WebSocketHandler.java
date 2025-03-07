package com.example.nmbapp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class WebSocketHandler extends WebSocketClient {
    private Listener listen;
    public static  boolean status = false;
    public WebSocketHandler(URI serverUri, Map<String, String> hm, Listener listener) {
        super(serverUri, hm);
        listen = listener;
//        this.tv = textView;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to server");
        status = true;
//        send("Hello Server!");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        listen.onMessageReceived(message);
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed");
        status = false;
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void sendMessage(String str){
        send(str);
    }

}

