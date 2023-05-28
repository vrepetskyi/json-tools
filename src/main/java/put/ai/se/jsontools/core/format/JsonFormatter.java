package put.ai.se.jsontools.core.format;

public abstract class JsonFormatter implements FormattableJson {
    private FormattableJson source;

    public JsonFormatter(FormattableJson source) {
        this.source = source;
    }

    @Override
    public String getProcessed(FormatArguments arguments) {
        return source.getProcessed(arguments);
    }
}
