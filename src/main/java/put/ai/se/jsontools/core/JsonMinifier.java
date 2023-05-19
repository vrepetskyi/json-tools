package put.ai.se.jsontools.core;

public class JsonMinifier extends JsonFormattableDecorator
{
    public JsonMinifier(JsonFormattable source) {
        super(source);
    }
    public JsonMinifier() {
        super();
    };

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params); // what is it? (value)
        String jsonString = "{\n" + // to be replaced
        "    \"name\": \"John\",\n" + // value.json or sth
        "    \"age\": 30,\n" +
        "    \"city\": \"New York\",\n" +
        "    \"country\": \"USA\",\n" +
        "    \"attributes\": {\n" +
        "        \"height\": 180,\n" +
        "        \"weight\": 75\n" +
        "    }\n" +
        "}\n";
        // TODO: process value in a proper way
        /*
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
            value = gson.toJson(jsonElement);
        } catch (JsonSyntaxException e) {
            System.out.println("Invalid JSON format");
        }

         */
        return value;
    }
}
