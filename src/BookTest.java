import java.util.ArrayList;
import java.util.Scanner;

public class BookTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();

        ArrayList<Book> books = bookDAO.getAllBooks();

        for(Book book : books){
            System.out.println(book);
        }
    }
}