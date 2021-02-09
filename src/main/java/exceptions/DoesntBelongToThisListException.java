package exceptions;

public class DoesntBelongToThisListException extends Exception {
    public DoesntBelongToThisListException(String errorMessage){
        super(errorMessage);
    }
}
