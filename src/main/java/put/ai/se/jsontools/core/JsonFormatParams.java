package put.ai.se.jsontools.core;

/**
 * The objective of the JsonFormatParams class is to provide a base class for
 * defining parameters used for formatting JSON strings. It encapsulates the
 * formatting options and provides methods to access these options.
 */
public class JsonFormatParams {
    protected JsonFilterParams filter;
    protected boolean prettify;

    /**
     * Retrieves the formatting style for JSON strings.
     *
     * @return A boolean value that is true in case we should apply
     *         {@link JsonPrettifier}; {@link JsonMinifier} otherwise.
     */
    public boolean getPrettify() {
        return prettify;
    }

    /**
     * Retrieves the filter parameters for JSON strings.
     *
     * @return A {@link JsonFilterParams} object used for storing filtering
     *         parameters.
     */
    public JsonFilterParams getFilter() {
        return filter;
    }
}
