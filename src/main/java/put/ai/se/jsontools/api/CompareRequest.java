package put.ai.se.jsontools.api;

import put.ai.se.jsontools.core.compare.CompareArguments;

public class CompareRequest {
    private String s1;
    private String s2;
    private CompareArguments arguments;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public CompareArguments getArguments() {
        return arguments;
    }

    public void setArguments(CompareArguments arguments) {
        this.arguments = arguments;
    }
}
