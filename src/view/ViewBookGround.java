package view;

import book.Book;
import book.Row;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ViewBookGround {

    private Book book;

    public ViewBookGround(Book book) {
        this.book = book;
    }

    public void run() {
        while (true) {
            String row = prompt("Введите данные пользователя в формате(\"фамилия имя отчество dd.mm.yyyy 79990000000 [m,f]\"): \n");
            var user = new Row();
            try {
                user.parse(row);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            user.writeToFile();

            String cmd = prompt("Хотите еще добавить пользователя (Y/N)?");
            if (cmd.equalsIgnoreCase("Y")) {
                continue;
            }
            break;
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
