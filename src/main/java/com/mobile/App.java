package com.mobile;

import com.mobile.server.MessagingServer;
import com.sun.corba.se.spi.activation.Server;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MessagingServer messagingServer = new MessagingServer();
        messagingServer.start();
    }
}
