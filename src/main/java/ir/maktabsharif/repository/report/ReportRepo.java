package ir.maktabsharif.repository.report;

import ir.maktabsharif.model.Book;

import java.util.List;

public interface ReportRepo {
    List<Book> findAllBooks();
}
