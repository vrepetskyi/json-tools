package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFormatParamsBuilder extends JsonFormatParams {
    public JsonFormatParamsBuilder() {
        super();
    }

    public void setStyleMode(JsonStyleMode value) {
        super.styleMode = value;
    }

    public void setFilterMode(JsonFilterMode value) {
        super.filterMode = value;
    }

    public void setFilterKeys(LinkedHashSet<String> value) {
        super.filterKeys = value;
    }
}
