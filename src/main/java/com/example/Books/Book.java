package com.example.Books;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private String author;
    private Date publishedDate;
    private String isbn;
    private String genre;
    private int quantity;
    private String shelfLocation;

    public Long getBookId() {
        return bookId;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter methods
}
