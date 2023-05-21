package put.ai.se.jsontools.api;

import java.util.LinkedHashSet;

public class Filter {
    private String mode;
    private LinkedHashSet<String> keys;

    public String getMode() {
        return mode;
    }

    public LinkedHashSet<String> getKeys() {
        return keys;
    }
}
