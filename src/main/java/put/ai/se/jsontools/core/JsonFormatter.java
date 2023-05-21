package put.ai.se.jsontools.core;

/**
 * It performs formatting operations such as minification, prettification, and filtering on the JSON string.
 */
public class JsonFormatter {
    /**
     * Formats the provided JSON string using the specified formatting parameters.
     *
     * @param source The JSON string to be formatted.
     * @param params The formatting parameters specifying the desired formatting options JsonFilterMode (Include or Exclude) and keys to be included or excluded , JsonStyleMode (Minify or Prettify).
     * @return The formatted JSON string.
     * @throws IllegalArgumentException If the provided source is not a valid JSON string or if there is an error during the formatting process.
     */
    public static String format(String source, JsonFormatParams params) throws IllegalArgumentException {
        JsonFormattable result;
        try {
            result = new JsonString(source);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The source is not in JSON format", e);
        }

        if (params.getFilterMode() != null) {
            result = new JsonFilter(result);
        }

        switch (params.getStyleMode()) {
            case Minify:
                result = new JsonMinifier(result);
                break;
            case Prettify:
                result = new JsonPrettifier(result);
                break;
        }

        return result.getValue(params);
    }
}
