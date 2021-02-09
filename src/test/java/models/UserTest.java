package models;

import exceptions.DueDatePassedException;
import exceptions.DuplicateBookInLibraryException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {

    private User user;
    private Book book1;
    private Book book2;
    private BorrowedBook borrowedBook1;
    private BorrowedBook borrowedBook2;

    @Before
    public void beforeTest() {
        this.book1 = Book.builder().title("title").author("author").build();
        this.book2 = Book.builder().title("title").author("author").build();
        this.user = User.builder().login("user1").borrowedBooks(new ArrayList<>()).build();
    }

    @Test(expected = DuplicateBookInLibraryException.class)
    public void shouldThrowDuplicateBookInLibraryException() throws DuplicateBookInLibraryException {
        this.user.borrow(this.book1);
        this.user.checkIfBookAlreadyInBooks(this.user.BorrowedBookListToBookList(this.user.getBorrowedBooks()), this.book2);
    }
}
