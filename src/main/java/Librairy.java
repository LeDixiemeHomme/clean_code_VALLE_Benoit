import java.util.List;

class Librairy {

    private List<Book> books;

    public Librairy(List<Book> books){

        this.books = books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public List<Book> getBooks(){
        return this.books;
    }
}
