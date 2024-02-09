package book;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByValue;

public final class Book {
    private static final HashMap<String, ArrayList<Row>> bookGround = new HashMap<>();

    public void add(String lastName, Row user) {
        var list = bookGround.get(lastName);
        if (list != null) {
            list.add(user);
            bookGround.put(lastName, list);
            return;
        }
        var tmp = new ArrayList<Row>();
        tmp.add(user);
        bookGround.put(lastName,tmp);
    }

    public ArrayList<Row> find(String lastName) {
        var list = bookGround.get(lastName);
        if (list != null) {
            return list;
        }
        return new ArrayList<>();
    }

    public static HashMap<String, ArrayList<Row>> getBookGround() {
        return bookGround;
    }

    public static void sortPhoneBook(){
        var sorted = bookGround.entrySet().stream()
                .sorted(comparingByValue(comparingInt(List::size)));
        System.out.println(Arrays.toString(Arrays.stream(sorted.toArray()).toArray()));
    }
}
