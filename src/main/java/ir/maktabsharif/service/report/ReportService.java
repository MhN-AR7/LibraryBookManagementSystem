package ir.maktabsharif.service.report;

import ir.maktabsharif.model.Book;

import java.math.BigDecimal;
import java.util.List;

public interface ReportService {
    List<Book> getAllBooks();
    int getBookCount();
    BigDecimal getAverageBookPrice();
    List<Book> getMostExpensiveBooks();
}
