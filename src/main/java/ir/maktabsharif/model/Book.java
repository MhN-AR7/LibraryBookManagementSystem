package ir.maktabsharif.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "books")
public class Book extends BaseModel<Long> {
    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;
    @Column(name = "published_year")
    private int publishedYear;
    private BigDecimal price;

    public Book(String title, String author, String isbn, int publishedYear, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        setPrice(price);
    }

    public Book() {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d | Title: %s | Author: %s
                ISBN: %s | Published Year: %d | Price: %.2f
                """, this.getId(), title, author, isbn, publishedYear, price);
    }
}
