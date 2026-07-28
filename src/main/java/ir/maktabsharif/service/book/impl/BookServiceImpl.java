package ir.maktabsharif.service.book.impl;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.book.BookRepo;
import ir.maktabsharif.repository.book.impl.BookRepoImpl;
import ir.maktabsharif.service.book.BookService;
import ir.maktabsharif.util.Rule;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo = new BookRepoImpl();

    @Override
    public Book register(Book book) throws BusinessException {
        validate(book);

        bookRepo.insert(book);

        return book;
    }

    @Override
    public Book getById(Long id) throws BusinessException {
        Rule.check(
                id <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found!"));
    }

    @Override
    public Book update(Book book) throws BusinessException {
        Rule.check(
                book.getId() <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        validate(book);

        Rule.check(
                !bookRepo.update(book),
                BookNotFoundException::new,
                "Book Not Found!"
        );

        return book;
    }

    @Override
    public void delete(Long id) throws BusinessException {
        Rule.check(
                id <= 0,
                InvalidDataException::new,
                "ID Must be Positive!"
        );

        Rule.check(
                !bookRepo.delete(id),
                BookNotFoundException::new,
                "Book Not Found!"
        );
    }

    @Override
    public void validate(Book book) throws BusinessException {
        Rule.check(
                book == null,
                InvalidDataException::new,
                "Book Cannot be Null!"
        );

        Rule.check(
                book.getTitle() == null || book.getTitle().isBlank(),
                InvalidDataException::new,
                "Book's Title Cannot be Null or Empty!"
        );

        Rule.check(
                book.getAuthor() == null || book.getAuthor().isBlank(),
                InvalidDataException::new,
                "Book's Author Cannot be Null or Empty!"
        );

        Rule.check(
                book.getIsbn() == null || book.getIsbn().isBlank(),
                InvalidDataException::new,
                "Book's ISBN Cannot be Null or Empty!"
        );

        Rule.check(
                book.getPublishedYear() <= 0 || book.getPublishedYear() > LocalDate.now().getYear(),
                InvalidDataException::new,
                "Invalid Book's Published Year!"
        );

        Rule.check(
                book.getPrice().compareTo(BigDecimal.ZERO) < 0,
                InvalidDataException::new,
                "Price Cannot be Negative!"
        );

        Rule.check(
                book.getAvailableCopies() < 0,
                InvalidDataException::new,
                "Available Copies Cannot be Negative!"
        );
    }
}
