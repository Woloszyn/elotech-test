package tech.woloszyn.Elotech.domain.usecase.helper;

import java.time.LocalDate;

public class BirthdayHelper {

    public static boolean isValidBirthday(LocalDate bday) {
        return !bday.isAfter(LocalDate.now());
    }
}