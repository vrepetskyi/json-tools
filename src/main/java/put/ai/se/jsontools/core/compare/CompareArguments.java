package put.ai.se.jsontools.core.compare;

public class CompareArguments {
    private String string1;
    private String string2;
    private boolean returnIdentical;

    public String getString1() {
        return string1;
    }

    public void setString1(String value) {
        string1 = value;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String value) {
        string2 = value;
    }

    public boolean isReturnIdentical() {
        return returnIdentical;
    }

    public void setReturnIdentical(boolean value) {
        returnIdentical = value;
    }
}
