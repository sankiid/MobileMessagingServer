package com.mobile.server;

import com.mobile.repo.CacheRepositoryServiceImpl;
import com.mobile.repo.IRepositoryService;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sandeep on 16/2/17.
 */
public class MessageHandler implements Runnable {

    private Socket socket;
    private MessagingServer messagingServer;
    private IRepositoryService service;

    public MessageHandler(Socket socket, MessagingServer messagingServer) {
        this.socket = socket;
        this.messagingServer = messagingServer;
        this.service = CacheRepositoryServiceImpl.getService();
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(String.valueOf(time).getBytes());
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
