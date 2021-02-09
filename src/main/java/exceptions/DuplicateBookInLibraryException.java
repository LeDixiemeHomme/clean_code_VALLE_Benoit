package exceptions;

public class DuplicateBookInLibraryException extends Exception {
    public DuplicateBookInLibraryException(String errorMessage){
        super(errorMessage);
    }
}
