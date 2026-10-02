import java.util.Scanner;

public class MemberTest {
    public static void main(String[] args) {
        MemberDAO memberDAO = new MemberDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID: ");
        int id = scanner.nextInt();

        System.out.print("Name: ");
        String name = scanner.next();

        memberDAO.addMember(id,name);

    }
}
