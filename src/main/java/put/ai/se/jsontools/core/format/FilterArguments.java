package put.ai.se.jsontools.core.format;

import java.util.LinkedHashSet;

/**
 * The JsonFilterParams class represents parameters for filtering JSON data.
 * It contains a set of keys and a flag indicating whether to exclude or include
 * those keys during filtering.
 */
public class FilterArguments {
    /**
     * The set of keys used for filtering JSON data.
     */
    private LinkedHashSet<String> keys;

    /**
     * A flag indicating whether to exclude the keys during filtering.
     */
    private boolean exclude;

    /**
     * Returns the set of keys used for filtering.
     *
     * @return the set of keys used for filtering JSON data
     */
    public LinkedHashSet<String> getKeys() {
        return keys;
    }

    public void setKeys(LinkedHashSet<String> keys) {
        this.keys = keys;
    }

    /**
     * Returns the exclusion flag indicating whether to exclude the keys during
     * filtering.
     *
     * @return true if the keys should be excluded during filtering, false otherwise
     */
    public boolean getExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }
}
