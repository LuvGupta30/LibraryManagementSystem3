public class Book {

    private final int bookID;
    private final String bookName;
    private final int bookCopies;

    Book(int bookID, String bookName, int bookCopies) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.bookCopies = bookCopies;
    }

    @Override public String toString() {return "ID: "+ bookID +" || "+ " Name: " + bookName + " || "+" Copies: "+ bookCopies;}

    int getBookCopies(){return this.bookCopies;}
}