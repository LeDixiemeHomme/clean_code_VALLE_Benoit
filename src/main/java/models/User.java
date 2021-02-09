package models;

import exceptions.DoesntBelongToThisListException;
import exceptions.DuplicateBookInLibraryException;
import exceptions.MaxNumberException;
import interfaces.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements IBookInteraction, IDuplicationAvoid, IApplicationTime, IBookConverter, IBorrowNumberLimit, IGiveBackBookBelonging {

    private final String login;
    private List<BorrowedBook> borrowedBooks;

    @Builder
    public User(String login, List<BorrowedBook> borrowedBooks) {
        this.login = login;
        this.borrowedBooks = borrowedBooks;
    }

    private void addBook(BorrowedBook borrowedBook) {
        try {
            this.checkIfBookAlreadyInBooks(borrowedBookListToBookList(this.borrowedBooks), borrowedBook);
            this.checkNumberLimit(this.borrowedBooks.size() + 1, 4);

            this.borrowedBooks.add(borrowedBook);
        } catch (DuplicateBookInLibraryException | MaxNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBook(BorrowedBook borrowedBookToRemove){
        try{
            this.checkBookBelonging(this.borrowedBooks, borrowedBookToRemove);

            for (BorrowedBook borrowedBook: this.borrowedBooks) {
                if(borrowedBook.getTitle().equals(borrowedBookToRemove.getTitle()) && borrowedBook.getAuthor().equals(borrowedBookToRemove.getAuthor()))
                    this.borrowedBooks.remove(borrowedBook);
            }
        } catch (DoesntBelongToThisListException e) {
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
    public void giveBack(Book book) {
        BorrowedBook borrowedBookToRemove = this.bookToBorrowedBook(book);
        this.deleteBook(borrowedBookToRemove);
    }

    @Override
    public Book borrowedBookToBook(BorrowedBook borrowedBook) {
        return new Book(borrowedBook.getTitle(), borrowedBook.getAuthor());
    }

    @Override
    public BorrowedBook bookToBorrowedBook(Book book) {
        return new BorrowedBook(book.getTitle(), book.getAuthor(), getNow());
    }

    @Override
    public List<Book> borrowedBookListToBookList(List<BorrowedBook> borrowedBooks) {
        List<Book> convertedBooks = new ArrayList<>();
        for (BorrowedBook borrowed : borrowedBooks) {
            convertedBooks.add(borrowedBookToBook(borrowed));
        }
        return convertedBooks;
    }

    @Override
    public List<BorrowedBook> bookListToBorrowedBookList(List<Book> books) {
        List<BorrowedBook> convertedBorrowedBooks = new ArrayList<>();
        for (Book book : books) {
            convertedBorrowedBooks.add(bookToBorrowedBook(book));
        }
        return convertedBorrowedBooks;
    }

    @Override
    public void checkNumberLimit(int number, int maxNumber) throws MaxNumberException {
        if(number > maxNumber){
            throw new MaxNumberException("You have reach " + maxNumber + " books !");
        }
    }

    @Override
    public void checkBookBelonging(List<BorrowedBook> borrowedBooks, BorrowedBook borrowedBookToRemove) throws DoesntBelongToThisListException {
        for (BorrowedBook borrowedBook: this.borrowedBooks) {
            if (borrowedBook.getTitle().equals(borrowedBookToRemove.getTitle()) && borrowedBook.getAuthor().equals(borrowedBookToRemove.getAuthor()))
                return;
        }
        throw new DoesntBelongToThisListException("This book doesn't belong to this list sorry!");
    }
}
