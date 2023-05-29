package put.ai.se.jsontools.core.compare;

import java.util.LinkedHashSet;
import java.util.stream.IntStream;

public class StringComparer {
    public static LinkedHashSet<Integer> getLineNumbers(String s1, String s2, CompareArguments arguments)
            throws IllegalArgumentException {

        CompareMode mode = arguments.getMode() == null ? CompareMode.different : arguments.getMode();

        String[] l1 = (s1 == null ? "" : s1).split("\\r?\\n");
        String[] l2 = (s2 == null ? "" : s2).split("\\r?\\n");

        int minLines = Math.min(l1.length, l2.length);
        int maxLines = Math.max(l1.length, l2.length);

        IntStream allLines = IntStream.range(0, maxLines);

        LinkedHashSet<Integer> result = new LinkedHashSet<>();

        allLines.forEach(i -> {
            if (i < minLines && l1[i].equals(l2[i])) {
                if (mode == CompareMode.identical) {
                    result.add(i + 1);
                }
            } else {
                if (mode == CompareMode.different) {
                    result.add(i + 1);
                }
            }
        });

        return result;
    }
}
