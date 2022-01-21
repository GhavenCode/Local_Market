package com.example.localmarket;

public class User {
    public String email, firstName, lastName, phone;

    public User(){

    }

    public User(String email, String firstName, String lastName, String phone)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
