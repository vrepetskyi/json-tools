package put.ai.se.jsontools.core.format;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class JsonFilterTest {
    @Test
    void getProcessed_ReturnsFilteredJson() {
        String formattedJson = "{ \"name\": \"John\", \"age\": 30, \"city\": \"New York\" }";
        String filteredJson = "{\"name\":\"John\",\"age\":30}";

        FormattableJson sourceMock = mock(FormattableJson.class);
        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(formattedJson);

        JsonFilter jsonFilter = new JsonFilter(sourceMock);

        FilterArguments filterArguments = new FilterArguments();
        LinkedHashSet<String> filterKeys = new LinkedHashSet<>();
        filterKeys.add("name");
        filterKeys.add("age");
        filterArguments.setKeys(filterKeys);

        FormatArguments formatArguments = new FormatArguments();
        formatArguments.setFilter(filterArguments);

        String result = jsonFilter.getProcessed(formatArguments);

        assertEquals(filteredJson, result);
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }

    @Test
    void getProcessed_ReturnsOriginalJson_WhenFilterIsNull() {
        String formattedJson = "{ \"name\": \"John\", \"age\": 30, \"city\": \"New York\" }";

        FormattableJson sourceMock = mock(FormattableJson.class);

        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(formattedJson);

        JsonFilter jsonFilter = new JsonFilter(sourceMock);

        FormatArguments formatArguments = new FormatArguments();
        formatArguments.setFilter(null);

        String result = jsonFilter.getProcessed(formatArguments);
        assertEquals(formattedJson, result);
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }

    @Test
    void getProcessed_ReturnsEmptyJson_WhenFilterIsEmptyAndExcludeIsFalse() {
        String originalJson = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";

        FormattableJson sourceMock = mock(FormattableJson.class);
        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(originalJson);

        JsonFilter jsonFilter = new JsonFilter(sourceMock);

        FilterArguments filterArguments = new FilterArguments();
        filterArguments.setKeys(new LinkedHashSet<>());
        filterArguments.setExclude(false);

        FormatArguments formatArguments = new FormatArguments();
        formatArguments.setFilter(filterArguments);

        String result = jsonFilter.getProcessed(formatArguments);

        assertEquals("{}", result);
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }

    @Test
    void getProcessed_ReturnsOriginalJson_WhenFilterIsEmptyAndExcludeIsTrue() {
        String originalJson = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

        FormattableJson sourceMock = mock(FormattableJson.class);

        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(originalJson);

        JsonFilter jsonFilter = new JsonFilter(sourceMock);
        FilterArguments filterArguments = new FilterArguments();
        filterArguments.setKeys(new LinkedHashSet<>());
        filterArguments.setExclude(true);

        FormatArguments formatArguments = new FormatArguments();
        formatArguments.setFilter(filterArguments);

        String result = jsonFilter.getProcessed(formatArguments);

        assertEquals(originalJson, result);
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }
}
