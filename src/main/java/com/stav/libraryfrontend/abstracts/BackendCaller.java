package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.models.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Book getBook(int bookId){
        String data = request("api/books/id/" + bookId);
        System.out.println("test " + data);
        JSONObject object = new JSONObject(data);
        return new Book(
                object.getInt("book_id"),
                object.getInt("library_id"),
                object.getString("title"),
                object.getString("description"),
                convertJSONArrayToStringArray(object.getJSONArray("authors")),
                convertJSONArrayToStringArray(object.getJSONArray("genres")),
                object.getString("isbn"),
                object.getString("published"),
                object.getInt("pages"),
                object.getString("language"),
                object.getString("image_source")
                );
    }

    public Book getBook(String isbn){
        String data = request("api/book_details/" + isbn);
        JSONObject object = new JSONObject(data);
        return new Book(
                0,
                0,
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

        /*String data = request("books/v1/volumes?q=isbn:" + isbn);
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
        return book;*/
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

    public List<Book> getBooks(String language, String releaseDate, String library, String searchType, String search){
        try {
            language = URLEncoder.encode(language, "UTF-8");
            releaseDate = URLEncoder.encode(releaseDate, "UTF-8");
            library = URLEncoder.encode(library, "UTF-8");
            searchType = URLEncoder.encode(searchType, "UTF-8");
            search = URLEncoder.encode(search, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("123 " + search);
        String data = request("api/book_details?language=" + language + "&releaseDate=" + releaseDate + "&library=" + library + "&searchType=" + searchType + "&search=" + search);
        if(data.equals("")){
            return new ArrayList<>();
        }
        JSONArray array = new JSONArray(data);
        List<Book> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            output.add(new Book(
                    0,
                    0,
                    object.getString("title"),
                    object.getString("description"),
                    convertJSONArrayToStringArray(object.getJSONArray("authors")),
                    convertJSONArrayToStringArray(object.getJSONArray("genres")),
                    object.getString("isbn"),
                    object.getString("published"),
                    object.getInt("pages"),
                    object.getString("language"),
                    object.getString("image_source")
            ));
        }
        return output;
    }

    /**
     * @return object that contains locations: [
     * name, amount
     * ]
     * */

    public JSONArray getAmountOfBookInLibraries(String isbn){
        String data = request("api/books/amount_in_libraries/" + isbn);
        System.out.println(data);
        return new JSONArray(data);
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

    public List<JSONObject> getLibraries(){
        String data = request("api/libraries");
        JSONArray array = new JSONArray(data);
        List<JSONObject> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            output.add(array.getJSONObject(i));
        }
        return output;
    }

    public List<LoanedBook> getLoanedBooksWithIsbn(String isbn){
        String data = request("api/loans/isbn/" + isbn);
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

    public boolean returnBook(int bookId){
        String data = request("api/loans/return_book/" + bookId);
        return Boolean.parseBoolean(data);
    }

    public int getAmountInQueue(String isbn){
        String data = request("api/book_queue/amount/" + isbn);
        if(data.equals("")){
            return 0;
        }
        return Integer.parseInt(data);
    }

    public List<BookQueue> getReservedBooks(int customerId){
        String data = request("api/book_queue/customer/" + customerId);
        JSONArray array = new JSONArray(data);
        List<BookQueue> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            output.add(new BookQueue(
                    object.getInt("book_id"),
                    object.getInt("customer_id"),
                    object.getString("queue_date")
            ));
        }
        return output;
    }

    public boolean isInQueue(String isbn, int customerId){
        String data = request("api/book_queue/in_queue?isbn=" + isbn + "&customerId=" + customerId);
        return Boolean.parseBoolean(data);
    }

    public boolean reserveBook(String isbn, int customerId){
        String data = request("api/book_queue/reserve?isbn=" + isbn + "&customerId=" + customerId);
        return Boolean.parseBoolean(data);
    }

    public boolean leaveQueue(String isbn){
        String data = request("api/book_queue/leave_queue/" + isbn);
        return Boolean.parseBoolean(data);
    }

    //Post
    /**
     * @return the book with the correct book_id
     * */

    public boolean loanBook(Book book, Customer customer, int libraryId){
        JSONObject object = new JSONObject();
        object.put("isbn", book.getIsbn());
        object.put("customer_id", customer.getCustomerId());
        object.put("library_id", libraryId);
        return Boolean.parseBoolean(post("api/loans/loan", object.toString()));
    }

    public boolean createCustomer(String firstName, String lastName, String mail, String password){
        String data = request("api/customers/create?firstName=" + firstName + "&lastName=" + lastName + "&mail=" + mail + "&password=" + password);
        return Boolean.parseBoolean(data);
    }

    public boolean createStaff(String firstName, String lastName, String userName, String password, String role){
        String data = request("api/employees/create?firstName=" + firstName + "&lastName=" + lastName + "&username=" + userName + "&password=" + password + "&role=" + role);
        return Boolean.parseBoolean(data);
    }

    public Customer loginCustomer(String email, String password){
        String data = request("api/customers/login?email=" + email + "&password=" + password);
        if(data.equals("")){
            return null;
        }
        JSONObject object = new JSONObject(data);
        return new Customer(object.getInt("customer_id"),
                object.getString("first_name"),
                object.getString("last_name"),
                object.getString("email"),
                object.getString("password")
                );
    }

    public Staff loginStaff(String email, String password){
        String data = request("api/employees/login?username=" + email + "&password=" + password);
        if(data.equals("")){
            return null;
        }
        JSONObject object = new JSONObject(data);
        return new Staff(object.getInt("employee_id"),
                object.getString("first_name"),
                object.getString("last_name"),
                object.getString("username"),
                object.getString("password"),
                object.getString("role")
        );
    }
    //

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
