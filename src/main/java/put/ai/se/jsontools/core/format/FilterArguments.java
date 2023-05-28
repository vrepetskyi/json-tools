package put.ai.se.jsontools.core.format;

import java.util.LinkedHashSet;

public class FilterArguments {
    private LinkedHashSet<String> keys;
    private boolean exclude;

    public LinkedHashSet<String> getKeys() {
        return keys;
    }

    public void setKeys(LinkedHashSet<String> keys) {
        this.keys = keys;
    }

    public boolean getExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }
}
