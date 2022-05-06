package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.models.Book;
import javafx.print.PrinterJob;
import javafx.scene.image.Image;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;

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
            URL url = new URL("https://www.googleapis.com/" + path);
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

    public Book getBook(String isbn){
        String data = request("books/v1/volumes?q=isbn:" + isbn);
        System.out.println(data);
        JSONObject object = new JSONObject(data);
        JSONArray array = new JSONArray(object.getJSONArray("items"));
        Book book = new Book(
                0,
                array.getJSONObject(0).getJSONObject("volumeInfo").getString("title"),
                array.getJSONObject(0).getJSONObject("volumeInfo").getString("description"),
                convertJSONArrayToStringArray(array.getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors")),
                convertJSONArrayToStringArray(array.getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("categories")),
                array.getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(1).getString("identifier"),
                array.getJSONObject(0).getJSONObject("volumeInfo").getString("publishedDate"),
                array.getJSONObject(0).getJSONObject("volumeInfo").getInt("pageCount"),
                array.getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail").replace("zoom=1", "zoom=10")
        );
        return book;
    }

    public Book[] getBooks(){
        String data = request("api/books");
        return null;
    }

    public Book[] getLoanedBooks(int customerId){
        String data = request("api/books/customer/" + customerId);
        return null;
    }

    private String[] convertJSONArrayToStringArray(JSONArray array){
        List<String> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            output.add(array.getString(i));
            System.out.println(array.getString(i));
            System.out.println(array);
        }
        return output.toArray(new String[output.size()]);
    }

}
