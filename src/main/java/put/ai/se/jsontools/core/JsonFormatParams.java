package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

/**
 * The objective of the JsonFormatParams class is to provide a base class for defining parameters used for formatting JSON strings. It encapsulates the formatting options and provides methods to access these options.
 */
public abstract class JsonFormatParams {
    protected JsonStyleMode styleMode;
    protected JsonFilterMode filterMode;
    protected LinkedHashSet<String> filterKeys;

    /**
     * Retrieves the formatting style mode for JSON strings.
     *
     * @return The formatting style mode as a {@link JsonStyleMode} enum value (Minify or Prettify).
     */
    public JsonStyleMode getStyleMode() {
        return styleMode;
    }

    /**
     * Retrieves the filtering mode for JSON strings.
     *
     * @return The filtering mode as a {@link JsonFilterMode} enum value (Include or Exclude).
     */
    public JsonFilterMode getFilterMode() {
        return filterMode;
    }

    /**
     * Retrieves the set of keys to be filtered in the JSON string.
     *
     * @return A {@link LinkedHashSet<String>} containing the keys to be included or excluded from the JSON string.
     */
    public LinkedHashSet<String> getFilterKeys() {
        return filterKeys;
    }
}
