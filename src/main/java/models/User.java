package models;

import exceptions.DueDatePassedException;
import exceptions.DuplicateBookInLibraryException;
import interfaces.IApplicationTime;
import interfaces.IBookConverter;
import interfaces.IBorrow;
import interfaces.IDuplicationAvoid;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements IBorrow, IDuplicationAvoid, IApplicationTime, IBookConverter {

    private final String login;
    private List<BorrowedBook> borrowedBooks;

    @Builder
    public User(String login, List<BorrowedBook> borrowedBooks) {
        this.login = login;
        this.borrowedBooks = borrowedBooks;
    }

    public void addBook(BorrowedBook book) {
        try {
            this.checkIfBookAlreadyInBooks(BorrowedBookListToBookList(this.borrowedBooks), book);
            this.borrowedBooks.add(book);
        } catch (DuplicateBookInLibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getLogin() {
        return login;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public void checkIfBookAlreadyInBooks(List<Book> books, Book book) throws DuplicateBookInLibraryException {
        for (Book it : books) {
            if (it.getTitle().equals(book.getTitle()) && it.getAuthor().equals(book.getAuthor())) {
                throw new DuplicateBookInLibraryException("This book os already in this library.");
            }
        }
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    @Override
    public void borrow(Book book) {
        this.addBook(new BorrowedBook(book.getTitle(), book.getAuthor(), getNow()));
    }

    @Override
    public Book BorrowedBookToBook(BorrowedBook borrowedBook) {
        return new Book(borrowedBook.getTitle(), borrowedBook.getAuthor());
    }

    @Override
    public List<Book> BorrowedBookListToBookList(List<BorrowedBook> books) {
        List<Book> convertedBooks = new ArrayList<>();
        for (BorrowedBook borrowed : books) {
            convertedBooks.add(BorrowedBookToBook(borrowed));
        }
        return convertedBooks;
    }
}