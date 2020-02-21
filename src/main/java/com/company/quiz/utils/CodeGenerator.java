package com.company.quiz.utils;

import java.security.SecureRandom;

public class CodeGenerator {

    private static SecureRandom random = new SecureRandom();

    private static final String NUMERIC = "0123456789";

    public static String generatePassword(int len, String dic) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    public static String generateCard(int len) {
        return generatePassword(len, NUMERIC);
    }

}
