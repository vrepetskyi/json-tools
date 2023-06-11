package put.ai.se.jsontools.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareResolver implements EndpointResolver {
    static final String FORMAT_ERROR = "Invalid request format. See documentation";
    static final String STRING_ERROR = "Keys \"s1\" and \"s2\" are required and should be strings";
    static final String ARGUMENTS_ERROR = "Key \"arguments\" is required and should be an object";
    static final String MODE_ERROR = "Argument \"mode\" is required and it's value should be either \"different\" or \"identical\"";

    @Override
    public String resolve(String plainReqBody) {
        if (plainReqBody == null)
            throw new IllegalArgumentException(FORMAT_ERROR);

        CompareRequest parsedReq;
        try {
            parsedReq = new Gson().fromJson(plainReqBody, CompareRequest.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException(FORMAT_ERROR, e);
        }

        String s1 = parsedReq.getS1();
        String s2 = parsedReq.getS2();
        CompareArguments arguments = parsedReq.getArguments();

        if (s1 == null || s2 == null)
            throw new IllegalArgumentException(STRING_ERROR);

        if (arguments == null)
            throw new IllegalArgumentException(ARGUMENTS_ERROR);

        if (arguments.getMode() == null)
            throw new IllegalArgumentException(MODE_ERROR);

        return StringComparer.getLineNumbers(s1, s2, arguments).toString();
    }
}
