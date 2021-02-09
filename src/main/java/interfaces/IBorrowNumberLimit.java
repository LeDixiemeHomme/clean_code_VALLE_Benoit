package interfaces;

import exceptions.MaxNumberException;

public interface IBorrowNumberLimit {
    void checkNumberLimit(int number, int maxNumber) throws MaxNumberException;
}
