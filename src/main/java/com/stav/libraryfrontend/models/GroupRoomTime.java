package com.stav.libraryfrontend.models;

public class GroupRoomTime {
    private int time_id;
    private int room_id;
    private String time;
    private String date;

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GroupRoomTime(int time_id, int room_id, String time, String date) {
        this.time_id = time_id;
        this.room_id = room_id;
        this.time = time;
        this.date = date;
    }
}
