package book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Row {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthday;
    private String phoneNumber;
    private String sex;

    final static String DATE_FORMAT = "dd.MM.yyyy";

    public void parse(String rowString) throws Exception {
        var parts = rowString.trim().toLowerCase().split(" ");
        try {
            validate(parts);
        }catch (Exception e){
            throw new Exception(e);
        }
        this.lastName = parts[0];
        this.firstName = parts[1];
        this.middleName = parts[2];

        this.birthday = new SimpleDateFormat(DATE_FORMAT).parse(parts[3]);
        this.phoneNumber = parts[4];
        this.sex = parts[5];
    }


    ///VALIDATOR
    private static boolean validate(String[] parts) throws Exception{
        if (parts.length != 6){
            throw new Exception("Формат строки не соответсвует формату");
        }
        if (parts[0].length() < 4 ||
                parts[1].length() < 3 ||
                parts[2].length() < 4){
            throw new Exception("ФИО не соответсвует формату");
        }
        if (!isDateValid(parts[3])){
            throw  new Exception("Указана некорректный формат даты");
        }
        if(!isPhoneValid(parts[4])){
           throw  new Exception("Указан некорректный формат телефона");
        }
        if (!isSexValid(parts[5])){
            throw new Exception("Указан некорректный формат пола");
        }


        return true;
    }

    private static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean isPhoneValid(String s) {
        String regex = "7\\d{10}"; // 7XXXXXXXXXX
        return s.matches(regex);
    }

    private static boolean isSexValid(String s) {
        if(s.length() == 1) {
            if (s.equals("m") || s.equals("f")) {
                return true;
            }
        }
        return false;
    }

    static String folder = "db_logs/";
    public void writeToFile(){
        Path currentRelativePath = Paths.get("");
        String dir = currentRelativePath.toAbsolutePath().toString();
        var path = new StringBuilder();
        path.append(dir).append("/").append(folder);
        File directory = new File(path.toString());
        if (! directory.exists()){
            directory.mkdir();
        }
        path.append("/")
                .append(this.lastName)
                .append(".txt");
        try (FileWriter writer = new FileWriter(path.toString(),true)){
            writer.write(this.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
    public String toString(){
        var out = new StringBuilder();
        out.append("<").append(this.lastName).append(">")
                .append("<").append(this.firstName).append(">")
                .append("<").append(this.middleName).append(">")
                .append("<").append(new SimpleDateFormat("dd.MM.yyyy").format(this.birthday)).append(">")
                .append(" <").append(this.phoneNumber).append(">")
                .append("<").append(this.sex).append(">");
        return out.toString();
    }
}

//ivanov kostya ivanovich 10.10.2022 79998887766 m
//lunina sveta ivanovich 10.10.2022 79998887766 f
