import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {

        Library librairy = new Library(new ArrayList<>());

        for (Book book : JsonParser.getJsonContent("./books")) {
            librairy.addBook(book);
        }

        System.out.println(librairy.getBooks());
    }
}
