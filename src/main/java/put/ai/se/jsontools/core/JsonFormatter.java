package put.ai.se.jsontools.core;

public class JsonFormatter {
    public static String format(String source, JsonFormatParams params) throws IllegalArgumentException {
        JsonFormattable result;

        try {
            result = new JsonString(source);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The source is not in JSON format", e);
        }

        result = new JsonFilter(result);

        if (params.getPrettify())
            result = new JsonPrettifier(result);
        else
            result = new JsonMinifier(result);

        return result.getValue(params);
    }
}
