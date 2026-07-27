package ir.maktabsharif.repository.book.impl;

import ir.maktabsharif.exception.DatabaseRepoException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.book.BookRepo;
import ir.maktabsharif.util.JpaConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

import java.util.Optional;

public class BookRepoImpl implements BookRepo {
    @Override
    public void insert(Book book) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            tx = em.getTransaction();

            tx.begin();
            em.persist(book);
            tx.commit();
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback(); //Searched With Ai
            throw new DatabaseRepoException("Insert Book to Database Failed: " + e.getMessage());
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Book book = em.find(Book.class, id);

            return Optional.ofNullable(book);
        }
        catch (PersistenceException e) {
            throw new DatabaseRepoException("Find Book From Database Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean update(Book book) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Book existingBook = em.find(Book.class, book.getId());

            if (existingBook == null) return false;

            tx = em.getTransaction();

            tx.begin();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPublishedYear(book.getPublishedYear());
            existingBook.setPrice(book.getPrice());
            tx.commit();
            return true;
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DatabaseRepoException("Update Book From Database Failed: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityTransaction tx = null;
        try (EntityManager em = JpaConfig.getEMF().createEntityManager()) {
            Book existingBook = em.find(Book.class, id);

            if (existingBook == null) return false;

            tx = em.getTransaction();

            tx.begin();
            em.remove(existingBook);
            tx.commit();
            return true;
        }
        catch (PersistenceException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DatabaseRepoException("Delete Book From Database Failed: " + e.getMessage());
        }
    }
}
