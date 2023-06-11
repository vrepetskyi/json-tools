package put.ai.se.jsontools.core.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonMinifierTest {
    private FormattableJson sourceMock;
    private JsonMinifier jsonMinifier;

    @BeforeEach
    void setUp() {
        sourceMock = mock(FormattableJson.class);
        jsonMinifier = new JsonMinifier(sourceMock);
    }

    @Test
    void getProcessed_ReturnsMinifiedJson() {
        String originalJson = "{ \"name\": \"John\", \"age\": 30 }";
        String minifiedJson = "{\"name\":\"John\",\"age\":30}";

        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(originalJson);

        String result = jsonMinifier.getProcessed(new FormatArguments());

        assertEquals(minifiedJson, result);
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }

    @Test
    void getProcessed_ReturnsEmptyString_WhenSourceReturnsNull() {
        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(null);

        String result = jsonMinifier.getProcessed(new FormatArguments());

        assertEquals(result, "null");
        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }
}
