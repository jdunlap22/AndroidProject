package com.example.androidproject;

public class Contacts {

    String firstname;
    String lastname;
    String email;
    String address;
    String phone;
    String notes;

    public Contacts(String firstname, String lastname, String email, String address, String phone, String notes) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.notes = notes;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

}
