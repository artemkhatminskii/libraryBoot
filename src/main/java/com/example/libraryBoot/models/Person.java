package com.example.libraryBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Book> books;

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "Укажите ФИО.")
    @Size(min = 2, max = 100, message = "Укажите корректное ФИО")
    @Column(name = "name")
    private String name;

    @Min(value = 1900, message = "Укажите корректный год рождения")
    @Column(name = "year")
    private int year;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
