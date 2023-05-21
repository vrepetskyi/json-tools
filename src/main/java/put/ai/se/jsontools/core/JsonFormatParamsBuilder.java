package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFormatParamsBuilder extends JsonFormatParams {
    public JsonFormatParamsBuilder() {
        super();
    }

    public void setPrettify(boolean value) {
        super.prettify = value;
    }

    public void setFilterMode(JsonFilterMode value) {
        super.filter.mode = value;
    }

    public void setFilterKeys(LinkedHashSet<String> value) {
        super.filter.keys = value;
    }
}
