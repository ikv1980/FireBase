package com.example.firebase;

public class User {
    public String id, firstName, lastName, email;

    public User(String id, String firstname, String lastName) {
    }

    public User(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
