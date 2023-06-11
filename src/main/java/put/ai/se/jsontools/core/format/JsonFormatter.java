/**
 * The `JsonFormatter` class is an abstract class that serves as a base for implementing JSON formatters.
 * It implements the `FormattableJson` interface.
 */
package put.ai.se.jsontools.core.format;

public abstract class JsonFormatter implements FormattableJson {
    private FormattableJson source;

    /**
     * Constructs a new `JsonFormatter` object with the provided source.
     *
     * @param source The source `FormattableJson` object.
     */
    public JsonFormatter(FormattableJson source) {
        this.source = source;
    }

    /**
     * Returns the processed JSON string based on the provided format arguments.
     *
     * @param arguments The format arguments.
     * @return The processed JSON string.
     */
    @Override
    public String getProcessed(FormatArguments arguments) {
        return source.getProcessed(arguments);
    }
}
