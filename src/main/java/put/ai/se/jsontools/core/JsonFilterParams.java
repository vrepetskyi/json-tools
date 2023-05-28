package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

/**
 * The JsonFilterParams class represents parameters for filtering JSON data.
 * It contains a set of keys and a flag indicating whether to exclude or include those keys during filtering.
 */
public class JsonFilterParams {
    /**
     * The set of keys used for filtering JSON data.
     */
    protected LinkedHashSet<String> keys;
    
    /**
     * A flag indicating whether to exclude the keys during filtering.
     */
    protected boolean exclude;

    /**
     * Returns the set of keys used for filtering.
     *
     * @return the set of keys used for filtering JSON data
     */
    public LinkedHashSet<String> getKeys() {
        return keys;
    }

    /**
     * Returns the exclusion flag indicating whether to exclude the keys during filtering.
     *
     * @return true if the keys should be excluded during filtering, false otherwise
     */
    public boolean getExclude() {
        return exclude;
    }
}
