package put.ai.se.jsontools.core.compare;

import java.util.LinkedHashSet;
import java.util.stream.IntStream;

public class StringComparer {
    public static LinkedHashSet<Integer> getLineNumbers(CompareArguments arguments) throws IllegalArgumentException {
        String s1 = arguments.getString1();
        String s2 = arguments.getString2();

        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("Arguments \"string1\" and \"string2\" are required");

        String[] l1 = s1.split("\\r?\\n");
        String[] l2 = s2.split("\\r?\\n");

        int minLines = Math.min(l1.length, l2.length);
        int maxLines = Math.max(l1.length, l2.length);

        IntStream allLines = IntStream.range(0, maxLines);

        LinkedHashSet<Integer> result = new LinkedHashSet<>();

        allLines.forEach(i -> {
            if (i < minLines && l1[i].equals(l2[i])) {
                if (arguments.isReturnIdenticalNumbers()) {
                    result.add(i + 1);
                }
            } else {
                if (!arguments.isReturnIdenticalNumbers()) {
                    result.add(i + 1);
                }
            }
        });

        return result;
    }
}
