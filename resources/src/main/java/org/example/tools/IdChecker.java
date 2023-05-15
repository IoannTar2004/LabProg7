package org.example.tools;

import org.example.collections.Dragon;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdChecker {
    /**
     * Method checks if the ID entered correctly. If this is not the case print mistake messages.
     * @param id ID
     * @return Long id if entered id consists of 12 numbers, else -1.
     */
    public static String check(Collection<Dragon> list, String id) {

        Pattern pattern = Pattern.compile("\\s*(\\d{12})\\s*");
        Matcher matcher = pattern.matcher(id);

        Pattern pattern1 = Pattern.compile("\\s*\\d{1,11}|\\d{13,}\\s*");
        Matcher matcher1 = pattern1.matcher(id);

        Pattern pattern3 = Pattern.compile("\\s*-\\d{12}\\s*");
        Matcher matcher3 = pattern3.matcher(id);

        Pattern pattern4 = Pattern.compile("\\s*-\\d{1,11}|-\\d{13,}\\s*");
        Matcher matcher4 = pattern4.matcher(id);

        Pattern pattern5 = Pattern.compile("\\s*-?\\d*\\.\\d+\\s*");
        Matcher matcher5 = pattern5.matcher(id);

        if (matcher.matches()) {
            Dragon dragon = list.stream().filter(d -> d.getId() == Long.parseLong(id)).findFirst().orElse(null);
            if (dragon == null) {return OutputText.error("DragonDoesNotExist");}
            else {return "Existed";}
        } else if (matcher1.matches()) {
            return OutputText.error("IdIncorrect1");
        } else if (matcher3.matches()) {
            return OutputText.error("IdIncorrect2");
        } else if (matcher4.matches()) {
            return OutputText.error("IdIncorrect3");
        } else if (matcher5.matches()) {
            return OutputText.error("IdIncorrect4");
        }  else {
            return OutputText.error("IdIncorrect5");
        }
    }

}
