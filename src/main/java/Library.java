import com.sun.jdi.request.DuplicateRequestException;
import lombok.Builder;

import java.util.List;

class Library {

    private List<Book> books;

    @Builder
    public Library(List<Book> books){

        this.books = books;
    }

    public void addBook(Book book){
        try {
            this.checkIfBookAlreadyInBooks(book);
            this.books.add(book);
        } catch (DuplicateBookInLibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getBooks(){
        return this.books;
    }

    public void checkIfBookAlreadyInBooks(Book book) throws DuplicateBookInLibraryException {
        for (Book it : this.books) {
            if(it.getTitle().equals(book.getTitle()) && it.getAuthor().equals(book.getAuthor())){
                throw new DuplicateBookInLibraryException("This book os already in this library.");
            }
        }
    }
}
