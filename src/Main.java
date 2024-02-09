import book.Book;
import view.ViewBookGround;

public class Main {
    public static void main(String[] args) {
        var book = new Book();
        ViewBookGround view = new ViewBookGround(book);
        view.run();
    }
}
