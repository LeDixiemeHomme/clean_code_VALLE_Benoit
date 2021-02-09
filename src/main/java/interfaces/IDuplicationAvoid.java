package interfaces;

import exceptions.DuplicateBookInLibraryException;
import models.Book;

import java.util.List;

public interface IDuplicationAvoid {
    void checkIfBookAlreadyInBooks(List<Book> books, Book book) throws DuplicateBookInLibraryException;
}
