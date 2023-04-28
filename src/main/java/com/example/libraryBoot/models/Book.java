package com.example.libraryBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @NotEmpty(message = "Укажите название книги.")
    @Size(min = 2, max = 200, message = "Укажите корректное название.")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Укажите автора.")
    @Size(min = 2, max = 100, message = "Укажите корректное ФИО автора.")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Укажите корректный год издания.")
    @Column(name = "year")
    private int year;

    @Column(name = "book_is_taken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(int book_id, String title, String author, int year) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
