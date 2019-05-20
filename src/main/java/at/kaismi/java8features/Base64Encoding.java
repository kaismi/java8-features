package at.kaismi.java8features;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class Base64Encoding {

    public static void main(String args[]) {
        try {
            System.out.println("Base 64 1:");
            // Encode using basic encoder
            String base64encodedString = java.util.Base64.getEncoder().encodeToString("Test String".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
            // Decode
            byte[] base64decodedBytes = java.util.Base64.getDecoder().decode(base64encodedString);
            System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
            System.out.println();

            System.out.println("Base 64 2:");
            base64encodedString = java.util.Base64.getUrlEncoder().encodeToString("Test String".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (URL) :" + base64encodedString);
            base64decodedBytes = java.util.Base64.getUrlDecoder().decode(base64encodedString);
            System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
            System.out.println();

            System.out.println("Base 64 3:");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = java.util.Base64.getMimeEncoder().encodeToString("Test String 2".getBytes("utf-8"));
            System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);
            base64decodedBytes = java.util.Base64.getMimeDecoder().decode(mimeEncodedString);
            System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));

        } catch (UnsupportedEncodingException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }

}
