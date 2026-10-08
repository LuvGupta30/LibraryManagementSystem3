import java.util.ArrayList;

public class AdminMenu {
    private final InputHandler inputHandler;
    private final MemberDAO memberDAO;
    private final BookDAO bookDAO;
    private final BorrowedBooksDAO borrowedBooksDAO;


    public AdminMenu(InputHandler inputHandler, MemberDAO memberDAO, BookDAO bookDAO, BorrowedBooksDAO borrowedBooksDAO ){
        this.inputHandler = inputHandler;
        this.memberDAO = memberDAO;
        this.bookDAO = bookDAO;
        this.borrowedBooksDAO = borrowedBooksDAO;
    }

    public void showMenu(Admin admin){

        System.out.println();
        System.out.println("Welcome " + admin.getUserName());

        while(true){
            ConsoleUI.printHeader("ADMIN MENU");

            System.out.println();

            System.out.println("MEMBERS");
            System.out.println(" 1. Add Member");
            System.out.println(" 2. Remove Member");
            System.out.println(" 3. Update Member");
            System.out.println(" 4. Find Member");
            System.out.println(" 5. View All Members");

            System.out.println();

            System.out.println("BOOKS");
            System.out.println(" 6. Add Book");
            System.out.println(" 7. Remove Book");
            System.out.println(" 8. Update Book");
            System.out.println(" 9. Search Book");
            System.out.println("10. Find Book");
            System.out.println("11. View All Books");

            System.out.println();

            System.out.println("BORROWING");
            System.out.println("12. View Borrowed Books");

            System.out.println();
            System.out.println("13. Log Out");

            ConsoleUI.printLine();

            int choice = inputHandler.readInt("Enter choice: ");

            switch (choice){

                case 1 -> {
                    int id = inputHandler.readInt("Enter ID: ");
                    String name = inputHandler.readString("Enter Name: ");

                    boolean added = memberDAO.addMember(id,name);

                    if(added){
                        System.out.println("Member added successfully!");
                    }
                    else{
                        System.out.println("Failed to add member :(");
                    }
                }

                case 2 -> {
                    int id = inputHandler.readInt("Enter ID: ");

                    Member member = memberDAO.getMemberById(id);

                    if(member == null){
                        System.out.println("Member does not exist!");
                        break;
                    }

                    boolean removed = memberDAO.removeMember(id);

                    if(removed){
                        System.out.println("Member removed successfully!");
                    }
                    else{
                        System.out.println("Failed to remove member :(");
                    }
                }

                case 3 -> {
                    int id = inputHandler.readInt("Enter ID: ");
                    String name = inputHandler.readString("Enter Name: ");

                    boolean updated = memberDAO.updateMember(id,name);

                    if(updated){
                        System.out.println("Member updated successfully!");
                    }
                    else{
                        System.out.println("Failed to update member :(");
                    }
                }

                case 4 -> {
                    int id = inputHandler.readInt("Enter ID: ");

                    Member findMember = memberDAO.getMemberById(id);

                    if (findMember != null) {
                        System.out.println(findMember);
                    } else {
                        System.out.println("Member not found.");
                    }

                }

                case 5 -> {
                    ArrayList<Member> members = memberDAO.getAllMembers();

                    if (members != null) {
                        for (Member member : members) {
                            System.out.println(member);
                        }
                    } else {
                        System.out.println("Unable to retrieve members.");
                    }
                }

                case 6 -> {
                    int id = inputHandler.readInt("Enter Book ID: ");
                    String name = inputHandler.readString("Enter Book Name: ");
                    int copies = inputHandler.readInt("Enter number of copies: ");

                    boolean added = bookDAO.addBook(id,name,copies);

                    if(added){
                        System.out.println("Book added successfully!");
                    }
                    else{
                        System.out.println("Failed to add book :(");
                    }
                }

                case 7 -> {
                    int id = inputHandler.readInt("Enter Book ID: ");

                    boolean removed = bookDAO.removeBook(id);

                    Book book = bookDAO.getBookById(id);

                    if(book == null){
                        System.out.println("Book with this ID does not exist!");
                        break;
                    }

                    if(removed){
                        System.out.println("Book removed successfully!");
                    }
                    else{
                        System.out.println("Failed to remove book :(");
                    }
                }

                case 8 -> {
                    int id = inputHandler.readInt("Enter Book ID: ");
                    String name = inputHandler.readString("Enter Book Name: ");
                    int copies = inputHandler.readInt("Enter number of copies: ");

                    boolean updated = bookDAO.updateBook(id,name,copies);

                    Book book = bookDAO.getBookById(id);

                    if (book == null) {
                        System.out.println("Book does not exist!");
                        break;
                    }

                    if(updated){
                        System.out.println("Book updated successfully!");
                    }
                    else{
                        System.out.println("Failed to update book :(");
                    }
                }

                case 9 -> {
                    String name = inputHandler.readString("Enter Book Name: ");

                    ArrayList<Book> books = bookDAO.getBooksByName(name);

                    if (books != null) {
                        if (books.isEmpty()) {
                            System.out.println("No books found.");
                        }

                        else {
                            for (Book book : books) {
                                System.out.println(book);
                            }
                        }
                    }

                    else {
                        System.out.println("Unable to retrieve books.");
                    }
                }

                case 10 -> {
                    int id = inputHandler.readInt("Enter ID: ");

                    Book findBook = bookDAO.getBookById(id);

                    if (findBook != null) {
                        System.out.println(findBook);
                    } else {
                        System.out.println("Member not found.");
                    }
                }

                case 11 -> {
                    ArrayList<Book> books = bookDAO.getAllBooks();

                    if (books != null) {
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    }

                    else {
                        System.out.println("Unable to retrieve borrowed books.");
                    }
                }

                case 12 -> {
                    ArrayList<BorrowedBook> borrowedBooks = borrowedBooksDAO.getAllBorrowedBooks();

                    if (borrowedBooks != null) {
                        for (BorrowedBook borrowedBook : borrowedBooks) {
                            System.out.println(borrowedBook);
                        }
                    } else {
                        System.out.println("Unable to retrieve borrowed books.");
                    }
                }

                case 13 -> {
                    System.out.println("Logging Out");
                    return;
                }
            }
        }
    }
}