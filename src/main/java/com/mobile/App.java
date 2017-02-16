package com.mobile;

import com.mobile.server.MessagingServer;
import com.mobile.server.utils.Constant;
import com.sun.corba.se.spi.activation.Server;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MessagingServer messagingServer = new MessagingServer(Constant.SERVER_PORT);
        messagingServer.start();
    }
}
