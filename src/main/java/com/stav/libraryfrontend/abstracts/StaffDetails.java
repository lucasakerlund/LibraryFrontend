package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.models.Customer;
import com.stav.libraryfrontend.models.Staff;

public class StaffDetails {
    private static StaffDetails instance = new StaffDetails();

    private Staff staff;

    private StaffDetails(){

    }

    public static StaffDetails inst(){
        return instance;
    }

    public Staff getStaff(){
        return staff;
    }

    public void setStaff(Staff staff){
        this.staff = staff;
    }
}
