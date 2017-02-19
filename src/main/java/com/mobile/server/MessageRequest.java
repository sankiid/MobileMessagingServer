package com.mobile.server;

import java.io.*;

/**
 * Created by sandeep on 19/2/17.
 */
public class MessageRequest {

    private InputStream inputStream;

    public MessageRequest() {
    }

    public MessageRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parseRequest() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = br.readLine()) != null/* && line.equalsIgnoreCase("exit")*/){
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean exit() {
        return false;
    }
}
