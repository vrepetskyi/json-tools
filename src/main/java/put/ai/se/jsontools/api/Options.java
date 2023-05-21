package put.ai.se.jsontools.api;

import put.ai.se.jsontools.core.JsonFilter;

public class Options {
    private Filter filter;
    private String minifyOrPrettify;

    public Filter getFilter() {
        return filter;
    }

    public String isMinifyOrPrettify() {
        return minifyOrPrettify;
    }
}
