import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

    public boolean addMember(int id, String name) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into members (id, name) values (?,?)")) {

            ps.setInt(1, id);
            ps.setString(2, name);

            int row = ps.executeUpdate();

            return row > 0;

        }

        catch (SQLException e) {
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }

    public Member getMemberById(int id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * from members where id = ?")) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
            int ID = resultSet.getInt("id");
            String name = resultSet.getString("name");

                Member member = new Member(ID,name);

                return member;

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

    public boolean removeMember(int id){

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from members where id = ?")) {

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

    public ArrayList<Member> getAllMembers(){

        ArrayList<Member> members = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("Select * from members")) {

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int ID = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Member member = new Member(ID, name);

                members.add(member);
            }
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return null;
        }

        return members;
    }

    public boolean updateMember(int id, String name){

        try(Connection connection =DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("update members set name = ? where id = ?")){

            ps.setString(1,name);
            ps.setInt(2,id);

            int row = ps.executeUpdate();

            return row > 0;
        }

        catch (SQLException e){
            System.out.println("ERROR!");
            e.printStackTrace();
            return false;
        }
    }
}