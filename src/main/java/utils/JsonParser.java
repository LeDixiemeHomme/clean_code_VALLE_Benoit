package utils;

import models.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<Book> getJsonContent(String filePath) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<Book> books = new ArrayList<>();

        try (FileReader reader = new FileReader("./books.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray bookList = (JSONArray) obj;
//            System.out.println(bookList);

            //Iterate over employee array
            bookList.forEach(emp -> books.add(parseBookObject((JSONObject) emp)));

        } catch (IOException |
                ParseException e) {
            e.printStackTrace();
        }
        return books;
    }

    private static Book parseBookObject(JSONObject book) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) book.get("book");

        //Get employee first name
        String title = (String) employeeObject.get("title");
//        System.out.println(firstName);

        //Get employee last name
        String author = (String) employeeObject.get("author");
//        System.out.println(lastName);

        return new Book(title, author);
    }

}
