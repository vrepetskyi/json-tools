package put.ai.se.jsontools.core.format;

import com.google.gson.JsonObject;

/**
 * It performs formatting operations such as minification, prettification, and
 * filtering on the JSON string.
 */
public class FormatDirector {
    /**
     * Formats the provided JSON string using the specified formatting parameters.
     *
     * @param source    The JSON string to be formatted.
     * @param arguments The formatting parameters specifying the desired formatting
     *                  options: keys to be included or excluded, and flags exclude
     *                  and
     *                  prettify.
     * @return The formatted JSON string.
     * @throws IllegalArgumentException If the provided source is not a valid JSON
     *                                  string or if there is an error during the
     *                                  formatting process.
     */
    public static String formatJson(String source, FormatArguments arguments) throws IllegalArgumentException {
        FormattableJson wrapped = new JsonValidator(source);

        wrapped = new JsonFilter(wrapped);

        if (arguments.getPrettify())
            wrapped = new JsonPrettifier(wrapped);
        else
            wrapped = new JsonMinifier(wrapped);

        return wrapped.getProcessed(arguments);
    }

    public static String formatJson(JsonObject source, FormatArguments arguments) throws IllegalArgumentException {
        return formatJson(source.toString(), arguments);
    }
}
