package tech.woloszyn.Elotech.domain.usecase.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailHelper {

    public static boolean isValid(String email) {
        Pattern pattern = Pattern.compile("([a-z])\\w+\\@([a-z])\\w+\\.([a-z])\\w+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
