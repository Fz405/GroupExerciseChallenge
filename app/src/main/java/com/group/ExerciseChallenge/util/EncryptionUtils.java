package com.group.ExerciseChallenge.util;

public class EncryptionUtils {
    private static final String ENCRYPTION_KEY = "secretkey";

    public static String encryptString(String input) {
        char[] key = ENCRYPTION_KEY.toCharArray();
        char[] data = input.toCharArray();
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            encrypted.append((char) (data[i] ^ key[i % key.length]));
        }

        return encrypted.toString();
    }

    public static String decryptString(String encrypted) {
        return encryptString(encrypted); // XOR encryption is symmetric
    }
}


