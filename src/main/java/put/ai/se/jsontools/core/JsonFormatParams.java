package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public abstract class JsonFormatParams {
    protected JsonStyleMode styleMode;
    protected JsonFilterMode filterMode;
    protected LinkedHashSet<String> filterKeys;

    public JsonStyleMode getStyleMode() {
        return styleMode;
    }

    public JsonFilterMode getFilterMode() {
        return filterMode;
    }

    public LinkedHashSet<String> getFilterKeys() {
        return filterKeys;
    }
}
