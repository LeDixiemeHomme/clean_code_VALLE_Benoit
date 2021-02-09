package interfaces;

import models.Book;
import models.BorrowedBook;

import java.util.List;

public interface IBookConverter {
    Book borrowedBookToBook(BorrowedBook borrowedBook);
    BorrowedBook bookToBorrowedBook(Book book);
    List<Book> borrowedBookListToBookList(List<BorrowedBook> borrowedBooks);
    List<BorrowedBook> bookListToBorrowedBookList(List<Book> books);
}
