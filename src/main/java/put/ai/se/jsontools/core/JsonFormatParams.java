package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFormatParams {
    private FilterMode filterMode;
    private LinkedHashSet<String> filterKeys;

    public JsonFormatParams(FilterMode filterMode, LinkedHashSet<String> filterKeys) {
        this.filterMode = filterMode;
        this.filterKeys = filterKeys;
    }

    public FilterMode getFilterMode() {
        return filterMode;
    }

    public LinkedHashSet<String> getFilterKeys() {
        return filterKeys;
    }
}
