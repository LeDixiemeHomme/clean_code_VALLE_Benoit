package interfaces;

import exceptions.DoesntBelongToThisListException;
import exceptions.DuplicateBookInLibraryException;
import models.Book;
import models.BorrowedBook;

import java.util.List;

public interface IGiveBackBookBelonging {
    void checkBookBelonging(List<BorrowedBook> borrowedBooks, BorrowedBook borrowedBookToRemove) throws DoesntBelongToThisListException;
}
