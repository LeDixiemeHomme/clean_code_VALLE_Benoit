package models;

import exceptions.DueDatePassedException;
import exceptions.DuplicateBookInLibraryException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;

    @Before
    public void beforeTest() {
        this.book1 = Book.builder().title("title").author("author").build();
        this.book2 = Book.builder().title("title").author("author").build();
        this.library = Library.builder().books(new ArrayList<>()).build();
    }

    @Test(expected = DuplicateBookInLibraryException.class)
    public void shouldThrowDuplicateBookInLibraryException() throws DuplicateBookInLibraryException {
        this.library.addBook(this.book1);
        this.library.checkIfBookAlreadyInBooks(this.library.getBooks(), this.book2);
    }

    @Test(expected = DueDatePassedException.class)
    public void shouldThrowDueDatePassedException() throws DueDatePassedException {
        this.library.limitDate(this.library.getNow(), this.library.getNow().minusWeeks(5));
    }
}
