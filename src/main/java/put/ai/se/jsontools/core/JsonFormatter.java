package put.ai.se.jsontools.core;

public class JsonFormatter {
    public static String format(String source, JsonFormatParams params) throws IllegalArgumentException {
        JsonFormattable result;
        try {
            result = new JsonString(source);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The source is not in JSON format", e);
        }

        switch (params.getStyleMode()) {
            case Minify:
                break;
            case Prettify:
                result = new JsonPrettifier(result);
                break;
        }

        return result.getValue(params);
    }
}
