package com.viktor.yurlov.entities;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "authors")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @ManyToMany(mappedBy ="authors", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Book> books;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private List<Reward> rewards;

    public Author(String firstName, String lastName, Sex sex, Date birthDay, List<Reward> rewards) {
        this.firstName=firstName;
        this.lastName = lastName;
        this.sex=sex;
        this.birthDate=birthDay;
        this.rewards=rewards;

    }

    public enum Sex{
       MALE,FEMALE
    }

    public Author() {
    }

    public Author(String firstName, String lastName, Sex sex, List<Book> books, Date birthDate, List<Reward> rewards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.books = books;
        this.birthDate = birthDate;
        this.rewards = rewards;
    }

    public Author(String firstName, String lastName, Sex sex, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Author(String firstName, String lastName, Sex sex, List<Book> books, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.books = books;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

}
