package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class DiffFinder {
    public static LinkedHashSet<Integer> getLineNumbers(String s1, String s2) {
        if (s1 == null)
            s1 = "";

        if (s2 == null)
            s2 = "";

        String[] lines1 = s1.split("\\r?\\n");
        String[] lines2 = s2.split("\\r?\\n");

        LinkedHashSet<Integer> differentLines = new LinkedHashSet<>();

        int numLines = Math.max(lines1.length, lines2.length);
        for (int i = 0; i < numLines; i++) {
            if (i >= lines1.length || i >= lines2.length || !lines1[i].equals(lines2[i])) {
                differentLines.add(i + 1);
            }
        }

        return differentLines;
    }
}
