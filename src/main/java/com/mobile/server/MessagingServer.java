package com.mobile.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sandeep on 16/2/17.
 */
public class MessagingServer {

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private volatile boolean stopped = false;
    private ExecutorService executorService = Executors.newFixedThreadPool(20, new ThreadFactory() {
        private AtomicInteger integer = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "server-thread-" + integer.getAndIncrement());
        }
    });

    public MessagingServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("starting server...");
        while (!stopped) {
            try {
                this.socket = serverSocket.accept();
                socket.setKeepAlive(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            executorService.execute(new MessageHandler(this.socket, this));
        }
    }

    public synchronized void shutdown() {
        this.stopped = true;
    }
}
