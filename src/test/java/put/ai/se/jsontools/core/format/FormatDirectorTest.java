package put.ai.se.jsontools.core.format;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatDirectorTest {
    @Test
    void format_Default_Arguments() {
        String expected = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"country\":\"USA\",\"attributes\":{\"height\":180,\"weight\":75}}";
        FormatArguments arguments = new FormatArguments();
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
        String actual = FormatDirector.formatJson(source, arguments);
        assertEquals(expected, actual);
    }

    @Test
    public void format_ValidJsonSource_PrettifyDisabled() {
        // Arrange
        String source = "{\"key\": \"value\"}";
        FormatArguments arguments = new FormatArguments();
        arguments.setPrettify(false);
        // Act
        String result = FormatDirector.formatJson(source, arguments);

        // Assert
        String expected = "{\"key\":\"value\"}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_ValidJsonSource_PrettifyEnabled() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        FormatArguments arguments = new FormatArguments();
        arguments.setPrettify(true);

        // Act
        String result = FormatDirector.formatJson(source, arguments);

        // Assert
        String expected = "{\n  \"key\": \"value\"\n}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_InvalidJsonSource_ThrowsIllegalArgumentException() {
        // Arrange
        String source = "invalid json";
        FormatArguments arguments = new FormatArguments();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FormatDirector.formatJson(source, arguments);
        });
    }

    @Test
    public void format_EmptyArguments_ReturnsSourceWithoutFormatting() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        FormatArguments arguments = new FormatArguments();

        // Act
        String result = FormatDirector.formatJson(source, arguments);

        // Assert
        String expected = "{\"key\":\"value\"}";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void format_NullArguments_UsesDefaultArguments() {
        // Arrange
        String source = "{\"key\":\"value\"}";
        FormatArguments arguments = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            FormatDirector.formatJson(source, arguments);
        });
    }

    @Test // doesn't work
    public void format_EmptySource_ReturnsEmptyString() {
        // Arrange
        String source = "";
        FormatArguments arguments = new FormatArguments();
        arguments.setPrettify(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FormatDirector.formatJson(source, arguments);
        });
    }

    @Test
    void format_includeFilter() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("name");
        set.add("job");

        FormatArguments arguments = new FormatArguments();
        FilterArguments filterArguments = new FilterArguments();
        arguments.setFilter(filterArguments);
        arguments.setPrettify(false);
        filterArguments.setExclude(false);
        filterArguments.setKeys(set);

        String expected = "{\"name\":\"Kowalski\",\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = FormatDirector.formatJson(source, arguments);

        assertEquals(expected, actual);
    }

    @Test
    void format_excludeFilter() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("age");
        FormatArguments arguments = new FormatArguments();
        FilterArguments filterArguments = new FilterArguments();
        arguments.setFilter(filterArguments);
        arguments.setPrettify(false);
        filterArguments.setExclude(true);
        filterArguments.setKeys(set);

        String expected = "{\"name\":\"Kowalski\",\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = FormatDirector.formatJson(source, arguments);

        assertEquals(expected, actual);
    }

    @Test
    void format_excludeFilter_empty_excludeList() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        // set.add("age");
        FormatArguments arguments = new FormatArguments();
        FilterArguments filterArguments = new FilterArguments();
        arguments.setFilter(filterArguments);
        arguments.setPrettify(false);
        filterArguments.setExclude(true);
        filterArguments.setKeys(set);

        String expected = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String source = "{\"name\":\"Kowalski\",\"age\":23,\"job\":\"Engineer\"}";
        String actual = FormatDirector.formatJson(source, arguments);

        assertEquals(expected, actual);
    }
}
