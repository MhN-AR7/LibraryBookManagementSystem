package ir.maktabsharif.console;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.exception.ThreadException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.service.book.BookService;
import ir.maktabsharif.service.book.impl.BookServiceImpl;
import ir.maktabsharif.service.member.MemberService;
import ir.maktabsharif.service.member.impl.MemberServiceImpl;
import ir.maktabsharif.service.report.ReportService;
import ir.maktabsharif.service.report.impl.ReportServiceImpl;
import ir.maktabsharif.threads.Consumer;
import ir.maktabsharif.threads.Producer;
import ir.maktabsharif.threads.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    static void main() {
        Scanner input = new Scanner(System.in);

        BookService bookService = new BookServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        ReportService reportService = new ReportServiceImpl();

        while (true) {
            System.out.println("""
                    ========== Library Book Management System ==========
                    1. Add Book
                    2. Find Book By ID
                    3. Update Book
                    4. Delete Book
                    5. Add Member
                    6. Find Member By ID
                    7. Update Member
                    8. Delete Member
                    9. Reports
                    10. Thread Exercises
                    0. Exit
                    """);

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("\n-------- Adding Book -------- \n");
                        System.out.println("Enter Book's Title: ");
                        String title = input.nextLine();
                        System.out.println("Enter Book's Author: ");
                        String author = input.nextLine();
                        System.out.println("Enter Book's ISBN: ");
                        String isb = input.nextLine();
                        System.out.println("Enter Book's Published Year: ");
                        int publishedYear = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter Book's Price: ");
                        BigDecimal price = input.nextBigDecimal();
                        input.nextLine();
                        System.out.println("Enter Book's Available Copies: ");
                        int availableCopies = input.nextInt();
                        input.nextLine();

                        Book book = bookService.register(
                                new Book(title, author, isb, publishedYear, price, availableCopies)
                        );
                        System.out.println("\nBook Added Successfully!\n" + book);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\n-------- Finding Book By ID --------\n");
                        System.out.println("Enter Book's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();

                        System.out.println(bookService.getById(id));
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\n-------- Updating Book --------");
                        System.out.println("Enter Book's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();
                        System.out.println("Enter New Title: ");
                        String title = input.nextLine();
                        System.out.println("Enter New Author: ");
                        String author = input.nextLine();
                        System.out.println("Enter New ISBN: ");
                        String isbn = input.nextLine();
                        System.out.println("Enter New Published Year: ");
                        int publishedYear = input.nextInt();
                        input.nextLine();
                        System.out.println("Enter New Price: ");
                        BigDecimal price = input.nextBigDecimal();
                        input.nextLine();
                        System.out.println("Enter New Available Copies: ");
                        int availableCopies = input.nextInt();
                        input.nextLine();

                        Book book = new Book(title, author, isbn, publishedYear, price, availableCopies);
                        book.setId(id);

                        Book newBook = bookService.update(book);
                        System.out.println("\nBook Updated Successfully!\n" + newBook);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\n-------- Deleting Book --------\n");
                        System.out.println("Enter Book's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();

                        bookService.delete(id);

                        System.out.println("\nBook Deleted Successfully!");
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("\n-------- Adding Member --------\n");
                        System.out.println("Enter Member's Full Name: ");
                        String fullName = input.nextLine();
                        System.out.println("Enter Member's Phone Number: ");
                        String phone = input.nextLine();
                        System.out.println("Enter Member's Email: ");
                        String email = input.nextLine();
                        System.out.println("Enter Member's Year of Birth: ");
                        int yearOfBirth = input.nextInt();
                        input.nextLine();

                        Member member = memberService.register(
                                new Member(fullName, phone, email, yearOfBirth)
                        );
                        System.out.println("\nMember Added Successfully!\n" + member);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("\n-------- Finding Member By ID --------\n");
                        System.out.println("Enter Member's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();

                        System.out.println(memberService.getById(id));
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("\n-------- Updating Member --------\n");
                        System.out.println("Enter Member's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();
                        System.out.println("Enter New Full Name: ");
                        String fullName = input.nextLine();
                        System.out.println("Enter New Phone Number: ");
                        String phone = input.nextLine();
                        System.out.println("Enter New Email: ");
                        String email = input.nextLine();
                        System.out.println("Enter New Year of Birth: ");
                        int yearOfBirth = input.nextInt();
                        input.nextLine();

                        Member member = new Member(fullName, phone, email, yearOfBirth);
                        member.setId(id);

                        Member newMember = memberService.update(member);
                        System.out.println("\nMember Updated Successfully!\n" + newMember);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("\n-------- Delete Member --------\n");
                        System.out.println("Enter Member's ID: ");
                        Long id = input.nextLong();
                        input.nextLine();

                        memberService.delete(id);

                        System.out.println("\nMember Deleted Successfully!");
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("\n-------- Reports --------\n");
                    System.out.println("\n---- All Books ----");
                    List<Book> allBooks = reportService.getAllBooks();
                    if (allBooks.isEmpty()) System.out.println("Book List is  Empty!");
                    else allBooks.forEach(System.out::println);
                    System.out.println("\nTotal Number of Books: " + reportService.getBookCount());
                    System.out.println("\nAverage Book Price: " + reportService.getAverageBookPrice());
                    System.out.println("\n---- Most Expensive Books ----");
                    List<Book> mostExpensiveBooks = reportService.getMostExpensiveBooks();
                    if (mostExpensiveBooks.isEmpty()) System.out.println("Book List is Empty!");
                    else mostExpensiveBooks.forEach(System.out::println);
                    break;
                case 10:
                    System.out.println("""
                            -------- Thread Exercises --------
                            1. Race Condition
                            2. Producer–Consumer
                            3. ExecutorService
                            """);
                    int threadChoice = input.nextInt();
                    input.nextLine();

                    switch (threadChoice) {
                        case 1:
                            try {
                                System.out.println("Enter Book's ID to Borrow for Race Condition: ");
                                Long id = input.nextLong();
                                input.nextLine();
                                Thread threadOne = new Thread(() -> {
                                    try {
                                        bookService.borrow(id);
                                    } catch (BusinessException e) {
                                        System.err.println(e.getMessage());
                                    }
                                });
                                Thread threadTwo = new Thread(() -> {
                                    try {
                                        bookService.borrow(id);
                                    } catch (BusinessException e) {
                                        System.err.println(e.getMessage());
                                    }
                                });

                                threadOne.start();
                                threadTwo.start();

                                threadOne.join();
                                threadTwo.join();

                                System.out.println("Book After Race Condition:\n" + bookService.getById(id));
                            }
                            catch (BusinessException e) {
                                System.err.println(e.getMessage());
                            }
                            catch (InterruptedException e) {
                                throw new ThreadException(e.getMessage());
                            }
                            break;
                        case 2:
                            try {
                                Warehouse warehouse = new Warehouse();

                                Thread producer = new Thread(new Producer(warehouse));
                                Thread consumer = new Thread(new Consumer(warehouse));

                                producer.start();
                                consumer.start();

                                producer.join();
                                consumer.join();

                                System.out.println("Producer-Consumer Finished!");
                            }
                            catch (InterruptedException e) {
                                throw new ThreadException(e.getMessage());
                            }
                            break;
                    }
            }
        }
    }
}
