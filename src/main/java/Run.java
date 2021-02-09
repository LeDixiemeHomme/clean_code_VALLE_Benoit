import models.Book;
import models.BorrowedBook;
import models.Library;
import models.User;
import utils.JsonParser;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {

        Library librairy = new Library(new ArrayList<>());

        for (Book book : JsonParser.getJsonContent("./books")) {
            librairy.addBook(book);
        }

        Book book1 = Book.builder().title("title").author("author").build();
        Book book2 = Book.builder().title("title").author("author").build();
        User user = User.builder().login("user1").borrowedBooks(new ArrayList<>()).build();

        user.borrow(book1);
    }
}
