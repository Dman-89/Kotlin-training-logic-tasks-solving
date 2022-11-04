package random.add_string_numbers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

//Given 2 strings which are intended to be numbers in any numeric system
//return sum of these 2 numbers in the same numeric system
public class AddStringNumbers {

    private static final Map<Character, Long> abcdefMap = new HashMap<>() {{
        put('A', 10L);
        put('B', 11L);
        put('C', 12L);
        put('D', 13L);
        put('E', 14L);
        put('F', 15L);
    }};

    private static final Map<Long, Character> mapInversed =
            abcdefMap.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public static void main(String[] args) {
        assertEquals("13438A4", addStringNumbers("FFDDAA", "345AFA", NumSys.HEX));
        assertEquals("4715A", addStringNumbers("12BAC", "345AE", NumSys.HEX));
        assertEquals("4726A", addStringNumbers("12BAC", "345AD", NumSys.FIFTEEN));
        assertEquals("133264", addStringNumbers("98675", "34589", NumSys.DECIMAL));
        assertEquals("16203", addStringNumbers("12736", "3245", NumSys.OCTAL));
        assertEquals("20343", addStringNumbers("10536", "6504", NumSys.SEVEN));
        assertEquals("20443", addStringNumbers("10535", "5504", NumSys.SIX));
        assertEquals("41223", addStringNumbers("31244", "4424", NumSys.FIVE));
        assertEquals("11211", addStringNumbers("2120", "3031", NumSys.FOUR));
        assertEquals("10010011", addStringNumbers("10100", "1111111", NumSys.BINARY));
    }


    private static String addStringNumbers(String s1, String s2, NumSys numsys) {
        if (numsys == NumSys.DECIMAL)
            return String.valueOf(Long.parseLong(s1) + Long.parseLong(s2));
        long resDecimal = convertToDecimal(s1, numsys.label) + convertToDecimal(s2, numsys.label);
        return convertToBase(resDecimal, numsys.label);
    }

    private static String convertToBase(long i, int base) {
        int power = (int) (Math.log(i) / Math.log(base));
        StringBuilder sb = new StringBuilder();
        while (i > base) {
            long currDivider = (int) Math.pow(base, power--);
            putToStringBuilder( i / currDivider, sb);
            i %= currDivider;
        }
        while (power-- > 0) sb.append(0);
        putToStringBuilder(i, sb);
        return sb.toString();
    }

    private static void putToStringBuilder(long currDigit, StringBuilder sb) {
        Character c = mapInversed.get(currDigit);
        if (c != null)
            sb.append(c);
        else
            sb.append(currDigit);
    }

    private static long convertToDecimal(String s, int base) {
        AtomicInteger powerS = new AtomicInteger(s.length() - 1);
        return (long) s.chars()
                .mapToObj(ch -> (char) ch)
                .map(ch -> {
                    Long currInt = abcdefMap.get(ch);
                    if (currInt != null)
                        return currInt * Math.pow(base, powerS.getAndDecrement());
                    else
                        return Integer.parseInt(ch.toString()) * Math.pow(base, powerS.getAndDecrement());
                })
                .reduce(0.0, Double::sum).doubleValue();
    }

}

enum NumSys {
    BINARY(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), OCTAL(8), NINE(9), DECIMAL(10),
    ELEVEN(11), TWELVE(12), THIRTEEN(13), FOURTEEN(14), FIFTEEN(15), HEX(16);

    NumSys(int label) {
        this.label = label;
    }

    final int label;
}
