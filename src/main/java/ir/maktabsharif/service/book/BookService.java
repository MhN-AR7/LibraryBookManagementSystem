package ir.maktabsharif.service.book;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.service.GenericService;

public interface BookService extends GenericService<Book, Long> {
    void borrow(Long id) throws BookNotFoundException;
}
