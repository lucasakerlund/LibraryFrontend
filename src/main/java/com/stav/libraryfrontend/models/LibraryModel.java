package com.stav.libraryfrontend.models;

public class LibraryModel {
    private String address;
    private String county;
    private int library_id;
    private String name;

    public LibraryModel(String address, String county, int library_id, String name) {
        this.address = address;
        this.county = county;
        this.library_id = library_id;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
