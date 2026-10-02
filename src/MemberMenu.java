import java.util.ArrayList;

public class MemberMenu {

    private final InputHandler inputHandler;
    private final BookDAO bookDAO;
    private final BorrowedBooksDAO borrowedBooksDAO;
    private final LibraryService libraryService;

    public MemberMenu(InputHandler inputHandler, BookDAO bookDAO, BorrowedBooksDAO borrowedBooksDAO, LibraryService libraryService) {

        this.inputHandler = inputHandler;
        this.bookDAO = bookDAO;
        this.borrowedBooksDAO = borrowedBooksDAO;
        this.libraryService = libraryService;
    }

    public void showMenu(Member member) {

        while (true) {

            ConsoleUI.printHeader("MEMBER MENU");

            System.out.println("BOOKS");
            System.out.println("1. Search Book");
            System.out.println("2. View All Books");

            System.out.println();

            System.out.println("BORROWING");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. My Borrowed Books");

            System.out.println();
            System.out.println("6. Log Out");

            ConsoleUI.printLine();

            int choice = inputHandler.readInt("Enter choice: ");

            switch (choice) {

                case 1 -> {
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

                case 2 -> {
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

                case 3 -> {
                    int bookID = inputHandler.readInt("Enter book id: ");
                    libraryService.issueBook(bookID, member.getID());
                }

                case 4 -> {
                    int bookId = inputHandler.readInt("Enter Book ID: ");
                    libraryService.returnBook(member.getID(), bookId);
                }

                case 5 -> {
                    ArrayList<BorrowedBook> borrowedBooks =
                            borrowedBooksDAO.getBorrowedBooksByMembers(member.getID());

                    if (borrowedBooks == null) {
                        System.out.println("Could not retrieve borrowed books.");
                    }

                    else if (borrowedBooks.isEmpty()) {
                        System.out.println("No books borrowed currently.");
                    }

                    else {
                        for (BorrowedBook borrowedBook : borrowedBooks) {
                            System.out.println(borrowedBook);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("Logging Out");
                    return;
                }
            }
        }
    }
}