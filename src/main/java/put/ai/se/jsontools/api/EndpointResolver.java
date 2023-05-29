package put.ai.se.jsontools.api;

public interface EndpointResolver {
    public String resolve(String plainReqBody) throws IllegalArgumentException;
}
