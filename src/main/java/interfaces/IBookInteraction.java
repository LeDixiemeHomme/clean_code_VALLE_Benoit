package interfaces;

import exceptions.DoesntBelongToThisListException;
import models.Book;

public interface IBookInteraction{
   void borrow(Book book);
   void giveBack(Book book) throws DoesntBelongToThisListException;

}
