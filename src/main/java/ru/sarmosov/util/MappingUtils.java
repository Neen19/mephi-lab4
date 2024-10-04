package ru.sarmosov.util;

import ru.sarmosov.entity.Person;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingUtils {


    public static Person parsePerson(String input) {
        String regex = "firstName=(.*?), lastName=(.*?), middleName=(.*?), birthDate=(\\d{4}-\\d{2}-\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            String middleName = matcher.group(3);
            String birthDate = matcher.group(4);

            return new Person(firstName, lastName, middleName, Date.valueOf(birthDate));
        } else {
            throw new IllegalArgumentException("Input string is not in the correct format");
        }
    }

    public static String removeFirstWord(String input) {
        int index = input.indexOf(' ');
        if (index != -1) {
            return input.substring(index + 1);
        }
        return "";
    }

    public static String extractId(String input) {
        int actionIndex = input.indexOf("action=\"/") + 8; // 8 - длина "action=\"/"

        int endIndex = input.indexOf("\"", actionIndex);

        if (actionIndex > 7 && endIndex > actionIndex) {
            return input.substring(actionIndex, endIndex);  // Извлекаем id
        }
        return null;
    }

}
