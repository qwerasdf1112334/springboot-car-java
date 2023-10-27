package cn.changge.base.utils;

import java.util.regex.Pattern;

public class EmployeeValidator {
    private static final String PHONE_REGEX = "^(\\+\\d{1,3})?\\d{8,12}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_REGEX, phoneNumber);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}