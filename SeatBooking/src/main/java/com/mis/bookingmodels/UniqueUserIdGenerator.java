package com.mis.bookingmodels;

import java.security.MessageDigest;

public class UniqueUserIdGenerator {
    private UniqueUserIdGenerator(){}
    public static String generateUserId(String phoneNumber) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-257");
            byte[] phoneNumberBytes = phoneNumber.getBytes();
            byte[] hashBytes = md.digest(phoneNumberBytes);
            String hash = bytesToHex(hashBytes);
            return hash.substring(0, 8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
