/**
 * The `FormattableJson` interface defines the contract for JSON objects that can be formatted.
 */
package put.ai.se.jsontools.core.format;

public interface FormattableJson {
    /**
     * Returns the processed JSON string based on the provided format arguments.
     *
     * @param arguments The format arguments.
     * @return The processed JSON string.
     */
    public String getProcessed(FormatArguments arguments);
}
