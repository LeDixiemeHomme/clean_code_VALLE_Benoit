import exceptions.DoesntBelongToThisListException;
import models.Book;
import models.Library;
import models.User;
import utils.JsonParser;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) throws DoesntBelongToThisListException {

        Library librairy = new Library(new ArrayList<>());

        for (Book book : JsonParser.getJsonContent("./books")) {
            librairy.addBook(book);
        }

        Book book1 = Book.builder().title("title1").author("author").build();
        Book book2 = Book.builder().title("title2").author("author").build();
        Book book3 = Book.builder().title("title3").author("author").build();
        Book book4 = Book.builder().title("title4").author("author").build();
        Book book5 = Book.builder().title("title5").author("author").build();

        User user1 = User.builder().login("user1").borrowedBooks(new ArrayList<>()).build();
        User user2 = User.builder().login("user2").borrowedBooks(new ArrayList<>()).build();

        user1.borrow(book1);
        user1.borrow(book2);
        user1.borrow(book3);
        user1.borrow(book4);
        user1.borrow(book5);

        user2.borrow(book1);
        System.out.println(user2.getBorrowedBooks());
        user2.borrow(book1);
        System.out.println(user2.getBorrowedBooks());
        user2.giveBack(book1);
        System.out.println(user2.getBorrowedBooks());


    }
}
