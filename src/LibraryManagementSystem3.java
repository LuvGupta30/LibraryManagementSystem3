import java.util.Scanner;

public class LibraryManagementSystem3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputHandler input = new InputHandler(scanner);
        MemberDAO memberDAO = new MemberDAO();
        AdminDAO adminDAO = new AdminDAO();
        BookDAO bookDAO = new BookDAO();
        BorrowedBooksDAO borrowedBooksDAO = new BorrowedBooksDAO();
        LibraryService libraryService = new LibraryService(bookDAO, borrowedBooksDAO, memberDAO);

        while (true) {
            ConsoleUI.printHeader("LIBRARY MANAGEMENT SYSTEM");

            System.out.println("1. Admin Login");
            System.out.println("2. Member Login");
            System.out.println("3. Exit");

            ConsoleUI.printLine();

            int choice = input.readInt("Enter choice: ");

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please select 1-3.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    int adminID = input.readInt("Enter your ID: ");

                    if (adminID <= 0) {
                        System.out.println("Invalid Login ID.");
                        continue;
                    }
                    Admin admin = adminDAO.getAdminById(adminID);

                    if (admin != null) {
                        AdminMenu adminMenu = new AdminMenu(input, memberDAO, bookDAO, borrowedBooksDAO);
                        adminMenu.showMenu(admin);
                    }

                    else {
                        System.out.println("Admin with ID " + adminID + " doesn't exist.");
                    }
                }

                case 2 -> {
                    int memberID = input.readInt("Enter your ID: ");

                    if (memberID <= 0) {
                        System.out.println("Invalid Login ID.");
                        continue;
                    }

                    Member member = memberDAO.getMemberById(memberID);

                    if (member != null) {
                        MemberMenu memberMenu = new MemberMenu( input, bookDAO, borrowedBooksDAO, libraryService);
                        memberMenu.showMenu(member);
                    } else {
                        System.out.println("Member not found :( ");
                    }
                }

                case 3 -> {
                    System.out.println("Exiting Program");
                    return;
                }
            }
        }
    }
}