package put.ai.se.jsontools.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import put.ai.se.jsontools.core.compare.CompareMode;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareResolver implements EndpointResolver {
    @Override
    public String resolve(String plainReqBody) {
        CompareRequest parsedReq;
        try {
            parsedReq = new Gson().fromJson(plainReqBody, CompareRequest.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("", e);
        }

        String s1 = parsedReq.getS1();
        String s2 = parsedReq.getS2();
        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("Keys \"s1\" and \"s2\" are required");

        CompareMode mode = parsedReq.getArguments().getMode();
        if (mode == null)
            throw new IllegalArgumentException("Argument \"mode\" should have a valid value");

        return StringComparer.getLineNumbers(s1, s2, parsedReq.getArguments()).toString();
    }
}
