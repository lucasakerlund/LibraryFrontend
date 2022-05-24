package com.stav.libraryfrontend.models;

public class GroupRoom {
    private int room_id;
    private String name;
    private int library_id;
    private String date;

    public GroupRoom(int room_id, String name, int library_id, String date) {
        this.room_id = room_id;
        this.name = name;
        this.library_id = library_id;
        this.date = date;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getName() {
        return name;
    }

    public int getLibrary_id() {
        return library_id;
    }

    public String getDate() {
        return date;
    }
}
