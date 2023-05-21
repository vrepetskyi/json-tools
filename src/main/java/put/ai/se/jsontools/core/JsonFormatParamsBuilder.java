package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

public class JsonFormatParamsBuilder extends JsonFormatParams {
    public JsonFormatParamsBuilder() {
        super();
    }

    public void setPrettify(boolean value) {
        super.prettify = value;
    }

    public void setExclude(boolean value) {
        super.filter.exclude = value;
    }

    public void setFilterKeys(LinkedHashSet<String> value) {
        super.filter.keys = value;
    }
}
