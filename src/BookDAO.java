import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {

    public boolean addBook(int id, String name, int copies){

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into books (id, name, copies) values (?,?,?)")){

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setInt(3,copies);

            int row = ps.executeUpdate();

            return row >0;
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }

    }

    public Book getBookById(int id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * from books where id = ?")) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int copies = resultSet.getInt("copies");

                Book book = new Book(ID,name,copies);

                return book;

            }

            else {
                return null;
            }
        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Book> getBooksByName(String name) {

        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE name LIKE ?")) {

            ps.setString(1, "%" + name + "%");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String bookName = resultSet.getString("name");
                int copies = resultSet.getInt("copies");

                Book book = new Book(id, bookName, copies);

                books.add(book);
            }

        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }

        return books;
    }

    public boolean removeBook(int id){

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from books where id = ?")) {

            ps.setInt(1,id);

            int row = ps.executeUpdate();

            return row > 0;
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Book> getAllBooks(){

        ArrayList<Book> books = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("Select * from books")) {

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int copies = resultSet.getInt("copies");

                Book book = new Book(ID, name, copies);
                books.add(book);
            }
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }

        return books;
    }

    public boolean updateBook(int id, String name, int copies){

        try(Connection connection =DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("update books set name = ?, copies = ? where id = ?")){

            ps.setString(1,name);
            ps.setInt(2,copies);
            ps.setInt(3,id);

            int row = ps.executeUpdate();

            return row > 0;

        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCopies(Connection connection, int id, int copies) {

        try (PreparedStatement ps = connection.prepareStatement("UPDATE books SET copies = ? WHERE id = ?")) {

            ps.setInt(1, copies);
            ps.setInt(2, id);

            int row = ps.executeUpdate();
            return row > 0;

        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }
}