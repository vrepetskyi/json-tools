package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFilterParams {
    protected JsonFilterMode mode;
    protected LinkedHashSet<String> keys;

    public JsonFilterMode getMode() {
        return mode;
    }

    public LinkedHashSet<String> getKeys() {
        return keys;
    }
}
