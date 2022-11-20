package codewars.not_very_secure;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://www.codewars.com/kata/526dbd6c8c0eb53254000110/train/java
public class SecureTester {

    public static void main(String[] args) {
        assertTrue(SecureTester.alphanumeric("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"));

        String[] input =  {"", "with space", "with_underscore", "punctuation.", "naïve", "１strangedigit", "emoji😊"};
        Arrays.stream(input).forEach(i -> assertFalse(SecureTester.alphanumeric(i)));
    }

    public static boolean alphanumeric(String s) {
        return s.matches("[a-zA-Z0-9]+");
    }
}
