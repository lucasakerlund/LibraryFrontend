package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.models.Customer;

public class UserDetails {

    private static UserDetails instance = new UserDetails();

    private Customer customer;

    private UserDetails(){

    }

    public static UserDetails inst(){
        return instance;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

}
