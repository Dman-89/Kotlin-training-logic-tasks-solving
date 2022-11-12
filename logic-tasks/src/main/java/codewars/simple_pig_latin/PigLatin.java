package codewars.simple_pig_latin;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.codewars.com/kata/520b9d2ad5c005041100000f/train/java
public class PigLatin {

    public static void main(String[] args) {
        assertEquals("igPay atinlay siay oolcay", pigIt("Pig latin is cool"));
        assertEquals("elloHay orldway !", pigIt("Hello world !"));

    }

    public static String pigIt(String str) {
        return Arrays.stream(str.split(" ")).map(s -> {
            if (!StringUtils.isAlphaSpace(s))
                return s;
            return s.substring(1) +
                    s.charAt(0) +
                    "ay";
        }).collect(Collectors.joining(" "));
    }
}
