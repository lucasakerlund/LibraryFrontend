package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.Library;
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
import java.util.Arrays;
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
        System.out.println("ej123 " + data);
        JSONObject object = new JSONObject(data);
        return new Book(
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
        );
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

    public List<Book> getBooks(String language, String releaseDate, String library, String searchType, String search, String popularSort){
        try {
            language = URLEncoder.encode(language, "UTF-8");
            releaseDate = URLEncoder.encode(releaseDate, "UTF-8");
            library = URLEncoder.encode(library, "UTF-8");
            searchType = URLEncoder.encode(searchType, "UTF-8");
            search = URLEncoder.encode(search, "UTF-8");
            popularSort = URLEncoder.encode(popularSort, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String data = request("api/book_details?language=" + language + "&releaseDate=" + releaseDate + "&library=" + library + "&searchType=" + searchType + "&search=" + search + "&popularSort=" + popularSort);
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

    public List<Book> getBooksByGenre(String[] genres){
        String genre = "";
        try {
            genre = URLEncoder.encode(String.join(",", genres), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(genre.equalsIgnoreCase("")){
            return new ArrayList<>();
        }
        String data = request("api/book_details/genre/" + genre);
        System.out.println(data);
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

    public LibraryModel getLibraryById(int libraryId){
        String data = request("api/libraries/" + libraryId);
        JSONObject o = new JSONObject(data);
        LibraryModel library = new LibraryModel(o.getString("address"),
                    o.getString("county"),
                    o.getInt("library_id"),
                    o.getString("name")
            );
        return library;
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
                    object.getString("isbn"),
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

    public boolean leaveQueue(String isbn, int customerId){
        String data = request("api/book_queue/leave_queue?isbn=" + isbn + "&customerId=" + customerId); 
        return Boolean.parseBoolean(data);
    }

    public List<GroupRoom> getGroupRooms(){
        String data = request("api/rooms/all");
        JSONArray allRooms = new JSONArray(data);

        List<GroupRoom> returnable = new ArrayList<GroupRoom>();

        for (int i = 0; i < allRooms.length(); i++){
            GroupRoom groupRoom = new GroupRoom (allRooms.getJSONObject(i).getInt("room_id"), allRooms.getJSONObject(i).getString("name"),
                    allRooms.getJSONObject(i).getInt("library_id"), allRooms.getJSONObject(i).getString("description"));

            returnable.add(groupRoom);
        }

        return returnable;
    }

    public List<GroupRoomTime> getGroupRoomTimes(int roomId){
        String data = request("api/group_room_times/available_times/" + roomId);
        JSONArray allTimes = new JSONArray(data);

        List<GroupRoomTime> returnable = new ArrayList<GroupRoomTime>();

        for (int i = 0; i < allTimes.length(); i++){
            GroupRoomTime groupRoomTime = new GroupRoomTime
                    (allTimes.getJSONObject(i).getInt("time_id"),
                            allTimes.getJSONObject(i).getInt("room_id"),
                            allTimes.getJSONObject(i).getString("time"),
                            allTimes.getJSONObject(i).getString("date"));

            returnable.add(groupRoomTime);
        }
        return returnable;
    }

    public List<JSONObject> getUsersGroupRoomTimesById(int customer_id){
        // We need all the group room times and then separate out the ones with my cus_id
        String data = request("api/group_room_times/get_times_by_id/" + customer_id);
        System.out.println("The String containing all bookings: " + data);

        JSONArray allUsersTimes = new JSONArray(data);

        List<JSONObject> returnable = new ArrayList<JSONObject>();

        for (int i = 0; i < allUsersTimes.length(); i++){
            JSONObject object = new JSONObject();
            object.put("time_id", allUsersTimes.getJSONObject(i).getInt("time_id"));
            object.put("time", allUsersTimes.getJSONObject(i).getString("time"));
            object.put("date", allUsersTimes.getJSONObject(i).getString("date"));
            object.put("name", allUsersTimes.getJSONObject(i).getString("name"));

            returnable.add(object);
        }
        return returnable;
    }

    public void getAllGroupRoomBookings(){
        String data = request("api/group_room_times/allRoomBookings");
        JSONArray allBookings = new JSONArray(data);

        System.out.println("Data: " + data);

        List<JSONObject> returnable = new ArrayList<>();

        for (int i = 0; i < allBookings.length(); i++) {
            JSONObject o = new JSONObject();
            o.put("customerId", allBookings.getJSONObject(i).getInt("customerId"));
            o.put("timeId", allBookings.getJSONObject(i).getInt("timeId"));

            returnable.add(o);
        }

        System.out.println("What the final list looks like: " + returnable);
    }

    public boolean bookGroupRoom(int timeId, int customerId){
        String data = request("api/group_room_times/book?timeId=" + timeId + "&customerId=" + customerId);
        return Boolean.parseBoolean(data);
    }

    public boolean removeGroupRoomBooking(int timeId, int customerId){
        String data = request("api/group_room_times/unbook?timeId=" + timeId + "&customerId=" + customerId);
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
        String data = request("api/employees/create?firstName=" + firstName + "&lastName=" + lastName + "&email=" + userName + "&password=" + password + "&role=" + role);
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
        String data = request("api/employees/login?email=" + email + "&password=" + password);

        if(data.equals("")){
            return null;
        }
        JSONObject object = new JSONObject(data);
        return new Staff(object.getInt("employee_id"),
                object.getString("first_name"),
                object.getString("last_name"),
                object.getString("email"),
                object.getString("password"),
                object.getString("role")
        );
    }

    public Customer getCustomerByEmail (String email){
        String data = request("api/customers/getCustomerByEmail?email=" + email);
        System.out.println("Returned in BackendCaller = " + data);

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


    private String[] convertJSONArrayToStringArray(JSONArray array){
        List<String> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            output.add(array.getString(i));
            System.out.println(array.getString(i));
            System.out.println(array);
        }
        return output.toArray(new String[output.size()]);
    }
    public boolean addBook(String title, String description, String authors, String genres, String isbn, String published, int page_count, String language, String image){
        String data = request("/api/book_details/add?title" + title + "description" + description + "authors" + authors + "genres" + genres + "isbn" + isbn + "published" + published + "page_count" + page_count + "language" + language + "image" + image);
        return Boolean.parseBoolean(data);
    }
}
