package put.ai.se.jsontools.core;

public class JsonFormatParams {
    protected JsonFilterParams filter;
    protected boolean prettify;

    public boolean getPrettify() {
        return prettify;
    }

    public JsonFilterParams getFilter() {
        return filter;
    }
}
