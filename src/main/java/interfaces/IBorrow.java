package interfaces;

import exceptions.DueDatePassedException;
import models.Book;
import models.BorrowedBook;

import java.time.LocalDateTime;

public interface IBorrow {
   void borrow(Book book);

}
