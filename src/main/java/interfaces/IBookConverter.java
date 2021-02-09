package interfaces;

import models.Book;
import models.BorrowedBook;

import java.util.List;

public interface IBookConverter {
    Book BorrowedBookToBook(BorrowedBook borrowedBook);
    List<Book> BorrowedBookListToBookList(List<BorrowedBook> books);
}
