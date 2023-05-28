package put.ai.se.jsontools.core.format;

/**
 * The objective of the JsonFormatParams class is to provide a base class for
 * defining parameters used for formatting JSON strings. It encapsulates the
 * formatting options and provides methods to access these options.
 */
public class FormatArguments {
    private FilterArguments filter = new FilterArguments();
    private boolean prettify;

    /**
     * Retrieves the filter parameters for JSON strings.
     *
     * @return A {@link FilterArguments} object used for storing filtering
     *         parameters.
     */
    public FilterArguments getFilter() {
        return filter;
    }

    /**
     * Retrieves the formatting style for JSON strings.
     *
     * @return A boolean value that is true in case we should apply
     *         {@link JsonPrettifier}; {@link JsonMinifier} otherwise.
     */
    public boolean getPrettify() {
        return prettify;
    }

    public void setPrettify(boolean prettify) {
        this.prettify = prettify;
    }
}
