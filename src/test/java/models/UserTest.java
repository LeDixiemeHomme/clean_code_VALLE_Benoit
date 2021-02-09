package models;

import exceptions.DoesntBelongToThisListException;
import exceptions.DuplicateBookInLibraryException;
import exceptions.MaxNumberException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {

    private Library library;
    private User user;
    private Book book1;
    private Book book1Bis;
    private BorrowedBook borrowedBook1;

    @Before
    public void beforeTest() {
        this.book1 = Book.builder().title("title1").author("author").build();
        this.book1Bis = Book.builder().title("title1").author("author").build();
        this.borrowedBook1 = BorrowedBook.borrowedBookBuilder().build();
        this.user = User.builder().login("user1").borrowedBooks(new ArrayList<>()).build();
    }

    @Test(expected = DuplicateBookInLibraryException.class)
    public void shouldThrowDuplicateBookInLibraryException() throws DuplicateBookInLibraryException {
        this.user.borrow(this.book1);
        this.user.checkIfBookAlreadyInBooks(this.user.borrowedBookListToBookList(this.user.getBorrowedBooks()), this.book1Bis);
    }

    @Test(expected = MaxNumberException.class)
    public void shouldThrowMaxNumberException() throws MaxNumberException {
        this.user.checkNumberLimit(5, 4);
    }

    @Test(expected = DoesntBelongToThisListException.class)
    public void shouldDoesntBelongToThisListException() throws DoesntBelongToThisListException {
        this.user.checkBookBelonging(this.user.getBorrowedBooks(), borrowedBook1);
    }
}
