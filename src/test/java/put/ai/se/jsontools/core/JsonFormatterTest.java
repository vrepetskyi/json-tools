package put.ai.se.jsontools.core;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonFormatterTest {

    @Test
    void format_Default_Params() {
        String expected = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"country\":\"USA\",\"attributes\":{\"height\":180,\"weight\":75}}";
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();
        String source = "{\n" +
        "  \"name\": \"John\",\n" +
        "  \"age\": 30,\n" +
        "  \"city\": \"New York\",\n" +
        "  \"country\": \"USA\",\n" +
        "  \"attributes\": {\n" +
        "    \"height\": 180,\n" +
        "    \"weight\": 75\n" +
        "  }\n" +
      "}";
        String actual = JsonFormatter.format(source, params);
        assertEquals(expected, actual);
    }

    
    @Test
    public void format_ValidJsonSource_PrettifyDisabled() {
        // Arrange
        String source = "{\"key\": \"value\"}";
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();
        params.setPrettify(false);
        // Act
        String result = JsonFormatter.format(source, params);

        // Assert
        String expected = "{\"key\":\"value\"}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_ValidJsonSource_PrettifyEnabled() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();
        params.setPrettify(true);

        // Act
        String result = JsonFormatter.format(source, params);

        // Assert
        String expected = "{\n  \"key\": \"value\"\n}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_InvalidJsonSource_ThrowsIllegalArgumentException() {
        // Arrange
        String source = "invalid json";
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JsonFormatter.format(source, params);
        });
    }

    @Test
    public void format_EmptyParams_ReturnsSourceWithoutFormatting() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        JsonFormatParams params = new JsonFormatParams();

        // Act
        String result = JsonFormatter.format(source, params);

        // Assert
        String expected = "{\"key\":\"value\"}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_NullParams_UsesDefaultParams() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        JsonFormatParams params = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonFormatter.format(source, params);
        });
    }

    @Test // doesn't work 
    public void format_EmptySource_ReturnsEmptyString() {
        // Arrange
        String source = "";
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();
        params.setPrettify(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            JsonFormatter.format(source, params);
        });
    }

    @Test
    void format_includeFilter() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("name");
        set.add("job");

        JsonFormatParamsBuilder filterParams = new JsonFormatParamsBuilder();
        filterParams.setPrettify(false);
        filterParams.setFilterMode(JsonFilterMode.Include);
        filterParams.setFilterKeys(set);
        
        String expected = "{\"name\":\"Kowalski\",\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = JsonFormatter.format(source, filterParams);

        assertEquals(expected, actual);
    }

    @Test
    void format_excludeFilter() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("age");
        JsonFormatParamsBuilder filterParams = new JsonFormatParamsBuilder();
        filterParams.setPrettify(false);
        filterParams.setFilterKeys(set);
        filterParams.setFilterMode(JsonFilterMode.Exclude);
        
        String expected = "{\"name\":\"Kowalski\",\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = JsonFormatter.format(source, filterParams);

        assertEquals(expected, actual);
    }

    @Test
    void format_excludeFilter_empty_excludeList() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        // set.add("age");
        JsonFormatParamsBuilder filterParams = new JsonFormatParamsBuilder();
        filterParams.setPrettify(false);
        filterParams.setFilterKeys(set);
        filterParams.setFilterMode(JsonFilterMode.Exclude);
        
        String expected = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = JsonFormatter.format(source, filterParams);

        assertEquals(expected, actual);
    }
}
