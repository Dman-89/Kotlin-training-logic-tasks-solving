package codewars.java_functional_programming_part4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


//https://www.codewars.com/kata/54ac045d35adf4e6e2000e43/train/java
public class DragonsCurve {

    static final List<Integer> ns = Arrays.asList(0, 1, 2, 3, 5);
    static final List<String> curves = Arrays.asList("F", "FRFR", "FRFRRLFLFR", "FRFRRLFLFRRLFRFRLLFLFR",
            "FRFRRLFLFRRLFRFRLLFLFRRLFRFRRLFLFRLLFRFRLLFLFRRLFRFRRLFLFRRLFRFRLLFLFRLLFRFRRLFLFRLLFRFRLLFLFR"
    );

    static Map<Integer, String> tests = new HashMap<>();

    public static void main(String[] args) {
        IntStream.range(0, ns.size()).forEach(i -> tests.put(ns.get(i), curves.get(i)));

        //testCreateCurve
        DragonsCurve testee = new DragonsCurve();
        tests.forEach((i, s) -> assertEquals(s, testee.createCurve(i)));

        //testMapFunction
        final List<Integer> ins = Arrays.asList((int)'a', (int)'b', (int)'F', (int)'R', (int)'L');
        final List<String> outs = Arrays.asList("aRbFR", "LFaLb", "F", "R", "L");
        Map<Integer, String> inOut = new HashMap<>(ins.size());
        IntStream.range(0, ins.size()).forEach(i -> inOut.put(ins.get(i), outs.get(i)));
        inOut.forEach((i, o) -> {
            String res = testee.mapFunction.apply(i);
            assertEquals(o, res);
        });

        //testHowMany
        final List<Long> fCounts = Arrays.asList(1L, 2L, 4L, 8L, 32L);
        final List<Long> rCounts = Arrays.asList(0L, 2L, 4L, 8L, 32L);
        final List<Long> lCounts = Arrays.asList(0L, 0L, 2L, 6L, 30L);
        for(int i = 0; i < curves.size(); i++) { //Some things are still better the old-fashioned way
            String p = curves.get(i);
            assertEquals(fCounts.get(i).longValue(), testee.howMany('F', p));
            assertEquals(rCounts.get(i).longValue(), testee.howMany('R', p));
            assertEquals(lCounts.get(i).longValue(), testee.howMany('L', p));
        }
    }

    //Make the function; map the chars to Strings
    //a -> aRbFR, b -> LFaLb, otherwise -> itself
    public IntFunction<String> mapFunction = value -> {
        if (value == 'a') return "aRbFR";
        else if (value == 'b') return "LFaLb";
        else return String.valueOf((char) value);
    };

    /**
     * Make the curve; stream the chars repeatedly (starting with Fa) through the mapFunction n times
     * Then remove the a and b (createFilter function is useful for that)
     */
    public String createCurve(int n) {
        String res = "Fa";
        for (int i = 0; i < n; i++)
            res = res.chars().mapToObj(mapFunction).collect(Collectors.joining());
        res = res.chars()
                .filter(createFilter('a', false))
                .filter(createFilter('b', false))
                .collect(StringBuilder::new,
                        (sb, i) -> sb.append((char)i),
                        StringBuilder::append
                ).toString();
        return res;
    }

    /**
     * How many of the specified char are in the given curve?
     * Hint: createFilter could be useful for this
     */
    public long howMany(char c, String curve) {
        return curve.chars()
                .filter(createFilter(c, true))
                .reduce(0, (subtotal, element) -> subtotal + 1);
    }

    /**
     * Create a predicate to filter the specified char; keep or remove based on keep variable
     */
    public IntPredicate createFilter(char filterWhat, boolean keep) {
        return value -> (value == filterWhat && keep) || (value != filterWhat && !keep); //Dat predicate
    }
}