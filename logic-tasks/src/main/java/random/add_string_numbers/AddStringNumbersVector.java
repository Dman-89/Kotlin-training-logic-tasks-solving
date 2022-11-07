package random.add_string_numbers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Given 2 strings which are intended to be numbers in any numeric system
//return sum of these 2 numbers in the same numeric system
public class AddStringNumbersVector {

    private static final Map<Character, Integer> alphabetMap = new HashMap<>() {{
        put('A', 10);
        put('B', 11);
        put('C', 12);
        put('D', 13);
        put('E', 14);
        put('F', 15);
        put('G', 16);
        put('H', 17);
        put('I', 18);
        put('J', 19);
        put('K', 20);
        put('L', 21);
        put('M', 22);
        put('N', 23);
        put('O', 24);
        put('P', 25);
        put('Q', 26);
        put('R', 27);
        put('S', 28);
        put('T', 29);
        put('U', 30);
        put('V', 31);
        put('W', 32);
        put('X', 33);
        put('Y', 34);
        put('Z', 35);
    }};

    private static final Map<Integer, Character> mapInversed =
            alphabetMap.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));


    public static void main(String[] args) {
        assertEquals("10W7F3HDGAV5QUWZ2NJINPK", addStringNumbers("ZXCWERTYUFSDFGHJKFDDAA", "YUIOPJHGFDDFGHJ345AFA", NumSys36.THIRTYSIX));
        assertEquals("ASDRBBA9861Q6JS5CE30SRQB", addStringNumbers("ASDFGHJKKJHFDSDFGHJHGFDS", "BNMJHGFDALKEIOPCCCCCC", NumSys36.TWENTYNINE));
        assertEquals("13438A4", addStringNumbers("FFDDAA", "345AFA", NumSys36.HEX));
        assertEquals("4715A", addStringNumbers("12BAC", "345AE", NumSys36.HEX));
        assertEquals("4726A", addStringNumbers("12BAC", "345AD", NumSys36.FIFTEEN));
        assertEquals("133264", addStringNumbers("98675", "34589", NumSys36.DECIMAL));
        //leading zeros in number
        assertEquals("2376857526280088", addStringNumbers("641705337", "002376856884574751", NumSys36.DECIMAL));
        assertEquals("10367", addStringNumbers("9567", "800", NumSys36.DECIMAL));
        assertEquals("81128108776873238361041931655", addStringNumbers("81128108776873238361041628545", "303110", NumSys36.DECIMAL));
        assertEquals("16203", addStringNumbers("12736", "3245", NumSys36.OCTAL));
        assertEquals("20343", addStringNumbers("10536", "6504", NumSys36.SEVEN));
        assertEquals("20443", addStringNumbers("10535", "5504", NumSys36.SIX));
        assertEquals("41223", addStringNumbers("31244", "4424", NumSys36.FIVE));
        assertEquals("11211", addStringNumbers("2120", "3031", NumSys36.FOUR));
        assertEquals("10010011", addStringNumbers("10100", "1111111", NumSys36.BINARY));
    }


    private static String addStringNumbers(String s1, String s2, NumSys36 numsys) {
        s1 = s1.replaceFirst("^0*", "");
        s2 = s2.replaceFirst("^0*", "");
        if (Math.min(s1.length(), s2.length()) == s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        String s1Reversed = new StringBuilder(s1).reverse().toString();
        String s2Reversed = new StringBuilder(s2).reverse().toString();
        List<Integer> s1Vector = stringToVector(s1Reversed);
        List<Integer> s2Vector = stringToVector(s2Reversed);
        for (int i = 0; i < s1Vector.size(); i++)
            s2Vector.set(i, s2Vector.get(i) + s1Vector.get(i));
        int nextOrderVal = 0;
        for (int i = 0; i < s2Vector.size(); i++) {
            int currVal = s2Vector.get(i);
            nextOrderVal = currVal / numsys.label;
            s2Vector.set(i, currVal % numsys.label);
            if (i == s2Vector.size() - 1)
                break;
            s2Vector.set(i + 1, s2Vector.get(i + 1) + nextOrderVal);
        }
        while (nextOrderVal != 0) {
            s2Vector.add(nextOrderVal % numsys.label);
            nextOrderVal = nextOrderVal / numsys.label;
        }
        Collections.reverse(s2Vector);
        return s2Vector.stream().map( num -> {
            if (mapInversed.get(num) != null)
                return mapInversed.get(num);
            else
                return (char)(num + '0');
        }).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString));
    }

    private static List<Integer> stringToVector(String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt)
                .map(ch -> {
                    Integer currInt = alphabetMap.get(ch);
                    return currInt != null ? currInt : Integer.parseInt(ch.toString());
                }).collect(Collectors.toList());
    }


}

enum NumSys36 {
    BINARY(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), OCTAL(8), NINE(9), DECIMAL(10),
    ELEVEN(11), TWELVE(12), THIRTEEN(13), FOURTEEN(14), FIFTEEN(15), HEX(16), SEVENTEEN(17), EIGHTEEN(18),
    TWENTYNINE(29), THIRTYSIX(36);

    NumSys36(int label) {
        this.label = label;
    }

    final int label;
}
