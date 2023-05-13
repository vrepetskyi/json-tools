package put.ai.se.jsontools.core;

public class JsonFilter extends JsonFormattableDecorator {
    public JsonFilter(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);
        // TODO: process value in a proper way
        return value;
    }

}
