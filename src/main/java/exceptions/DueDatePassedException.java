package exceptions;

public class DueDatePassedException extends Exception {
    public DueDatePassedException(String errorMessage){
        super(errorMessage);
    }
}
