package ir.maktabsharif.repository.report.impl;

import ir.maktabsharif.exception.DatabaseRepoException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.report.ReportRepo;
import ir.maktabsharif.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportRepoImpl implements ReportRepo {
    @Override
    public List<Book> findAllBooks() {
        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT * FROM books ORDER BY id"
                );
                ResultSet rs = ps.executeQuery()
        ) {
            List<Book> books = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("published_year"),
                        rs.getBigDecimal("price"),
                        rs.getInt("available_copies")
                );
                book.setId(rs.getLong("id"));

                books.add(book);
            }

            return books;
        }
        catch (SQLException e) {
            throw new DatabaseRepoException("Find All Books From Database Failed: " + e.getMessage());
        }
    }
}
