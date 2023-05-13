package put.ai.se.jsontools.core;

public abstract class JsonFormattableDecorator implements JsonFormattable {
    private JsonFormattable source;

    public JsonFormattableDecorator(JsonFormattable source) {
        this.source = source;
    }

    @Override
    public String getValue(JsonFormatParams params) {
        return source.getValue(params);
    }
}
