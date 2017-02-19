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
            MessageRequest request = new MessageRequest(socket.getInputStream());
            MessageResponse response = new MessageResponse(socket.getOutputStream());
            while (!request.exit()) {
                request.parseRequest();
                long time = System.currentTimeMillis();
                String message = String.valueOf(time + "\n");
                response.write(message);
//                Thread.sleep(10*1000);
            }
            request.close();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
