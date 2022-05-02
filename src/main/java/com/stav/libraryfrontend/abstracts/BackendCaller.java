package com.stav.libraryfrontend.abstracts;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BackendCaller {

    private static BackendCaller instance = new BackendCaller();

    private HttpURLConnection connection;

    private BackendCaller(){



    }

    public static BackendCaller inst(){
        return instance;
    }

    private String request(String path){
        String output = "";

        try {
            URL url = new URL("http://localhost:8080/" + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status < 300){
                Scanner scanner = new Scanner(connection.getInputStream());
                while(scanner.hasNextLine()){
                    output += scanner.nextLine();
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

}
