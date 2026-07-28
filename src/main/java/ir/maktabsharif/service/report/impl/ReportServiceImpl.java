package ir.maktabsharif.service.report.impl;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.report.ReportRepo;
import ir.maktabsharif.repository.report.impl.ReportRepoImpl;
import ir.maktabsharif.service.report.ReportService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    ReportRepo reportRepo = new ReportRepoImpl();

    @Override
    public List<Book> getAllBooks() {
        return reportRepo.findAllBooks();
    }

    @Override
    public int getBookCount() {
        return (int) reportRepo.findAllBooks()
                .stream()
                .count();
    }

    @Override
    public BigDecimal getAverageBookPrice() {
        List<Book> books = reportRepo.findAllBooks();

        double average = books
                .stream()
                .map(Book::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0);

        return BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<Book> getMostExpensiveBooks() {
        List<Book> books = reportRepo.findAllBooks();

        BigDecimal maxPrice = books
                .stream()
                .map(Book::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return books
                .stream()
                .filter(book -> book.getPrice().compareTo(maxPrice) == 0)
                .toList();
    }
}
