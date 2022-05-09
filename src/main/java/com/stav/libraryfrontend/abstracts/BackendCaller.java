package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.models.Book;
import com.stav.libraryfrontend.models.Customer;
import com.stav.libraryfrontend.models.LoanedBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    private String post(String path, String body){
        try {
            URL url = new URL("http://localhost:8080/" + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Book getBook(int bookId){
        String data = request("api/books/id/" + bookId);
        JSONObject object = new JSONObject(data);
        return new Book(
                object.getInt("book_id"),
                object.getString("title"),
                object.getString("description"),
                convertJSONArrayToStringArray(object.getJSONArray("authors")),
                convertJSONArrayToStringArray(object.getJSONArray("genre")),
                object.getString("isbn"),
                object.getString("published"),
                object.getInt("pages"),
                object.getString("language"),
                object.getString("image_source")
                );
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
                array.getJSONObject(0).getJSONObject("volumeInfo").getString("language"),
                array.getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail").replace("zoom=1", "zoom=10")
        );
        return book;
    }

    public int getAmountOfBooks(String isbn){
        String data = request("api/books/amount_with_isbn/" + isbn);
        System.out.println(data);
        return Integer.parseInt(data);
    }

    public int getAmountOfBooksInStock(String isbn){
        String data = request("api/books/amount_in_stock/" + isbn);
        return Integer.parseInt(data);
    }

    public int getAmountInQueue(String isbn){
        String data = request("api/book_queue/amount/" + isbn);
        return Integer.parseInt(data);
    }

    public Book[] getBooks(){
        String data = request("api/books");
        return null;
    }

    public List<LoanedBook> getLoanedBooks(int customerId){
        String data = request("api/loans/" + customerId);
        JSONArray array = new JSONArray(data);
        List<LoanedBook> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            output.add(new LoanedBook(
                    object.getInt("book_id"),
                    object.getInt("customer_id"),
                    object.getString("loan_date"),
                    object.getString("return_date")
                    ));
        }
        return output;
    }

    public int returnBook(int bookId){
        String data = request("api/loans/return_book/" + bookId);
        return Integer.parseInt(data);
    }

    /**
     * @return the book with the correct book_id
     * */

    public Book loanBook(Book book, Customer customer){
        JSONObject object = new JSONObject();
        object.put("isbn", book.getIsbn());
        object.put("customer_id", customer.getCustomerId());
        post("fruit", object.toString());
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
