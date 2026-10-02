import java.time.LocalDateTime;

public class BorrowedBook {
    private final int book_id;
    private final int member_id;
    private final LocalDateTime borrowed_at;

    BorrowedBook(int book_id, int member_id, LocalDateTime borrowed_at){
        this.book_id = book_id;
        this.member_id =member_id;
        this.borrowed_at = borrowed_at;
    }

    @Override public String toString(){return "Book ID: "+book_id+" || "+ "Member ID: "+ member_id+" || "+"Issued at: "+borrowed_at;}

}