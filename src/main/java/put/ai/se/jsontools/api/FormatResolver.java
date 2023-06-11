package put.ai.se.jsontools.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import put.ai.se.jsontools.core.format.FormatDirector;

public class FormatResolver implements EndpointResolver {
    static final String FORMAT_ERROR = "Invalid request format. See documentation";
    static final String SOURCE_ERROR = "Key \"source\" is required and should be an object";
    static final String ARGUMENTS_ERROR = "Key \"arguments\" is required and should be an object";

    @Override
    public String resolve(String plainReqBody) throws IllegalArgumentException {
        if (plainReqBody == null)
            throw new IllegalArgumentException(FORMAT_ERROR);

        FormatRequest parsedReq;
        try {
            parsedReq = new Gson().fromJson(plainReqBody, FormatRequest.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException(FORMAT_ERROR, e);
        }

        if (parsedReq.getSource() == null)
            throw new IllegalArgumentException(SOURCE_ERROR);

        if (parsedReq.getArguments() == null)
            throw new IllegalArgumentException(ARGUMENTS_ERROR);

        return FormatDirector.formatJson(parsedReq.getSource(), parsedReq.getArguments());
    }
}
