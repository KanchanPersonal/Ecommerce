package com.ecom.core.utilites;

import java.util.Random;

public class Utlities {

    // Random string with only alphabets, alphanumeric, - length
    // randomString()String type, length) --> return string

    public String generateRandomString(String type, int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphanumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String characters = "";
        if (type.equals("alphabets")) {
            characters = alphabets;
        } else if (type.equals("alphanumeric")) {
            characters = alphanumeric;
        }

        if (characters.isEmpty()) {
            return "Invalid type"; // No valid characters selected
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    // Other utility methods can be added here
}