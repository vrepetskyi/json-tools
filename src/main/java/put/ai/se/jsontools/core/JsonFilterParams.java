package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFilterParams {
    protected LinkedHashSet<String> keys;
    protected boolean exclude;

    public LinkedHashSet<String> getKeys() {
        return keys;
    }

    public boolean getExclude() {
        return exclude;
    }
}
