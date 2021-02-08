import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {

        Librairy librairy = new Librairy(new ArrayList<Book>());

        for (Book book : JsonParser.getJsonContent("./books")) {
            librairy.addBook(book);
        }

        System.out.println(librairy.getBooks());

    }
}
