package com.stav.libraryfrontend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    @JsonProperty("customer_id")
    private int customerId;
    @JsonProperty("fist_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("e-mail")
    private String rmail;
    @JsonProperty("password")
    private String password;

    public Customer(int customerId, String firstName, String lastName, String rmail, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rmail = rmail;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return rmail;
    }

    public void setEmail(String mail) {
        this.rmail = rmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
