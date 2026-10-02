import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BorrowedBooksDAO {

    public boolean borrowBook(Connection connection, int bookId, int memberId) {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO borrowed_books (book_id, member_id) VALUES (?, ?)")) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);

            int row = ps.executeUpdate();
            return row > 0;

        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(Connection connection, int memberId, int bookId) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM borrowed_books WHERE member_id = ? AND book_id = ?")) {

            ps.setInt(1, memberId);
            ps.setInt(2, bookId);

            int row = ps.executeUpdate();
            return row > 0;

        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<BorrowedBook> getAllBorrowedBooks(){

        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("Select * from borrowed_books")) {

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int book_id = resultSet.getInt("book_id");
                int member_id = resultSet.getInt("member_id");
                LocalDateTime borrowed_at = resultSet.getObject("borrowed_at", LocalDateTime.class);

                BorrowedBook borrowedBook = new BorrowedBook(book_id, member_id,borrowed_at);
                borrowedBooks.add(borrowedBook);
            }
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }
        return borrowedBooks;
    }

    public ArrayList<BorrowedBook> getBorrowedBooksByMembers (int member_id){

        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from borrowed_books where member_id = ?")){

            ps.setInt(1,member_id);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                int bookID = resultSet.getInt("book_id");
                int memberID = resultSet.getInt("member_id");
                LocalDateTime borrowed_at = resultSet.getObject("borrowed_at", LocalDateTime.class);

                BorrowedBook borrowedBook = new BorrowedBook(bookID, memberID, borrowed_at);
                borrowedBooks.add(borrowedBook);
            }
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }

        return borrowedBooks;
    }

    public boolean hasBorrowedBook(int memberId, int bookId) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT * FROM borrowed_books WHERE member_id = ? AND book_id = ?")) {

            ps.setInt(1, memberId);
            ps.setInt(2, bookId);

            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Could not check borrowing record.");
            return false;
        }
    }
}