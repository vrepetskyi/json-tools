package put.ai.se.jsontools.core.compare;

public class CompareArguments {
    private String string1;
    private String string2;
    private boolean returnIdenticalNumbers;

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public boolean isReturnIdenticalNumbers() {
        return returnIdenticalNumbers;
    }

    public void setReturnIdenticalNumbers(boolean returnIdentical) {
        this.returnIdenticalNumbers = returnIdentical;
    }

}
