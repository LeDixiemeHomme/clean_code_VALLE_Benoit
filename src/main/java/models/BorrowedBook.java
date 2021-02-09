package models;

import interfaces.IBookConverter;

import java.time.LocalDateTime;

public class BorrowedBook extends Book{

    private final LocalDateTime borrowDate;

    public BorrowedBook(String title, String author, LocalDateTime borrowDate) {
        super(title, author);
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String toString(){
        return "BorrowedBook{" +
                "title = '" + super.getTitle() + '\'' +
                ", author = " + super.getAuthor() + '\'' +
                ", date = " + borrowDate +
                '}';
    }
}
