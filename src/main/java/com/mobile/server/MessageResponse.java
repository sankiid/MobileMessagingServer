package com.mobile.server;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sandeep on 19/2/17.
 */
public class MessageResponse {


    private OutputStream outputStream;

    public MessageResponse() {
    }

    public MessageResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void close() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String message) {
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
