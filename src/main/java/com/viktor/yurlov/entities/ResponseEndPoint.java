package com.viktor.yurlov.entities;

import java.util.List;

public class ResponseEndPoint {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> books;

    public ResponseEndPoint(String firstName, String lastName, int age, List<String> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.books = books;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
