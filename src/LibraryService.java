import java.sql.Connection;
import java.sql.SQLException;

public class LibraryService {
    private final BookDAO bookDAO;
    private final BorrowedBooksDAO borrowedBooksDAO;
    private final MemberDAO memberDAO;

    LibraryService(BookDAO bookDAO, BorrowedBooksDAO borrowedBooksDAO, MemberDAO memberDAO){
        this.bookDAO = bookDAO;
        this.borrowedBooksDAO = borrowedBooksDAO;
        this.memberDAO = memberDAO;
    }

    public boolean issueBook(int book_id, int member_id){

        Member member = memberDAO.getMemberById(member_id);
        Book book = bookDAO.getBookById(book_id);

        if(member == null){
            System.out.println("Member doesn't exist ");
            return false;
        }

        if (book == null){
            System.out.println("Book doesn't exist");
            return false;
        }

        if(book.getBookCopies() <= 0){
            System.out.println("Book is currently unavailable");
            return false;
        }

        if (borrowedBooksDAO.hasBorrowedBook(member_id, book_id)) {
            System.out.println("You have already borrowed this book.");
            return false;
        }

        try (Connection connection = DBConnection.getConnection()) {

            try {
                connection.setAutoCommit(false);

                boolean copiesUpdated = bookDAO.updateCopies(connection, book_id, book.getBookCopies() - 1);

                if (!copiesUpdated) {
                    connection.rollback();
                    return false;
                }

                boolean borrowAddBook = borrowedBooksDAO.borrowBook(connection, book_id, member_id);

                if (!borrowAddBook) {
                    connection.rollback();
                    return false;
                }

                connection.commit();
                System.out.println("Book issued successfully!");
                return true;
            }

            catch(SQLException e){
                connection.rollback();
                System.out.println("Failed to issue book");
                e.printStackTrace();
                return false;
            }
        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int memberId, int bookId) {

        Member member = memberDAO.getMemberById(memberId);
        Book book = bookDAO.getBookById(bookId);


        if (member == null) {
            System.out.println("Member doesn't exist");
            return false;
        }

        if (book == null) {
            System.out.println("Book doesn't exist");
            return false;
        }

        try (Connection connection = DBConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);

                boolean returned = borrowedBooksDAO.returnBook(connection, memberId, bookId);

                if (!returned) {
                    System.out.println("This member has not borrowed this book.");
                    connection.rollback();
                    return false;
                }

                boolean copiesUpdated = bookDAO.updateCopies(connection, bookId,book.getBookCopies() + 1);

                if (!copiesUpdated) {
                    connection.rollback();
                    return false;
                }

                connection.commit();
                System.out.println("Book returned successfully.");
                return true;

            }

            catch (SQLException e) {
                connection.rollback();
                System.out.println("Could not return book.");
                e.printStackTrace();
                return false;
            }

        }

        catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
            return false;
        }
    }
}