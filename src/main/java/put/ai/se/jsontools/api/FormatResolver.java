package put.ai.se.jsontools.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import put.ai.se.jsontools.core.format.FormatDirector;

public class FormatResolver implements EndpointResolver {
    @Override
    public String resolve(String plainReqBody) throws IllegalArgumentException {
        FormatRequest parsedReq;
        try {
            parsedReq = new Gson().fromJson(plainReqBody, FormatRequest.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Key \"arguments\" should be a JSON object", e);
        }

        if (parsedReq.getSource() == null)
            throw new IllegalArgumentException("Key \"source\" is required");

        if (parsedReq.getArguments() == null)
            throw new IllegalArgumentException("Key \"arguments\" is required");

        return FormatDirector.formatJson(parsedReq.getSource(), parsedReq.getArguments());
    }
}
