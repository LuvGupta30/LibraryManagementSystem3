import java.time.LocalDateTime;
import java.util.ArrayList;

public class BorrowedBooksTest {
    public static void main(String[] args) {
        BorrowedBooksDAO borrowedBooksDAO = new BorrowedBooksDAO();

        ArrayList<BorrowedBook> borrowedBooks =
                borrowedBooksDAO.getBorrowedBooksByMembers(101);

        for (BorrowedBook borrowedBook : borrowedBooks) {
            System.out.println(borrowedBook);
        }
    }
}
