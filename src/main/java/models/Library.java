package models;

import exceptions.DueDatePassedException;
import exceptions.DuplicateBookInLibraryException;
import interfaces.IApplicationTime;
import interfaces.IDueDate;
import lombok.Builder;
import interfaces.IDuplicationAvoid;

import java.time.LocalDateTime;
import java.util.List;

public class Library implements IDuplicationAvoid, IDueDate, IApplicationTime {

    private List<Book> books;

    @Builder
    public Library(List<Book> books){

        this.books = books;
    }

    public void addBook(Book book){
        try {
            this.checkIfBookAlreadyInBooks(this.books, book);
            this.books.add(book);
        } catch (DuplicateBookInLibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getBooks(){
        return this.books;
    }

    public void checkIfBookAlreadyInBooks(List<Book> books, Book book) throws DuplicateBookInLibraryException {
        for (Book it : books) {
            if(it.getTitle().equals(book.getTitle()) && it.getAuthor().equals(book.getAuthor())){
                throw new DuplicateBookInLibraryException("This book os already in this library.");
            }
        }
    }

    @Override
    public boolean limitDate(LocalDateTime now, LocalDateTime borrowDate) throws DueDatePassedException {
        if (now.minusWeeks(4).isAfter(borrowDate)) {
            throw new DueDatePassedException("The due date has passed. Hurry !");
        }
        return true;
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
