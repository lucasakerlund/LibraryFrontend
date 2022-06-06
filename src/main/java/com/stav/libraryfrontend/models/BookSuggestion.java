package com.stav.libraryfrontend.models;

public class BookSuggestion {

    private int bookSuggestionId;
    private String isbn;
    private String title;
    private String author;
    private String language;

    public BookSuggestion(int bookSuggestionId, String isbn, String title, String author, String language) {
        this.bookSuggestionId = bookSuggestionId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.language = language;
    }

    public int getBookSuggestionId() {
        return bookSuggestionId;
    }

    public void setBookSuggestionId(int bookSuggestionId) {
        this.bookSuggestionId = bookSuggestionId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
