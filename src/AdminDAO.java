import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public Admin getAdminById(int id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * from admin where id = ?")) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Admin admin = new Admin(ID,name);

                return admin;

            }

            else {
                System.out.println("No admin found with such id");
                return null;
            }
        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }
    }
}