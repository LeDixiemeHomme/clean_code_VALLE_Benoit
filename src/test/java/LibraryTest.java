
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LibraryTest {

    private Book book1;
    private Book book2;
    private Library library;

    @Before
    public void beforeTest() {
        this.book1 = Book.builder().title("title").author("author").build();
        this.book2 = Book.builder().title("title").author("author").build();
        this.library = Library.builder().books(new ArrayList<>()).build();
    }

    @Test(expected = DuplicateBookInLibraryException.class)
    public void shouldThrowDuplicateBookInLibraryException() throws DuplicateBookInLibraryException {
        this.library.addBook(this.book1);
        this.library.checkIfBookAlreadyInBooks(this.book2);
    }
}
