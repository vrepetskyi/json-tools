package put.ai.se.jsontools.core.format;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JsonPrettifierTest {
    @Test
    void getProcessed_ReturnsPrettifiedJson() {
        // Arrange
        String originalJson = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String formattedJson = "{\n  \"name\": \"John\",\n  \"age\": 30,\n  \"city\": \"New York\"\n}";

        FormattableJson sourceMock = mock(FormattableJson.class);
        when(sourceMock.getProcessed(any(FormatArguments.class))).thenReturn(originalJson);

        JsonPrettifier jsonPrettifier = new JsonPrettifier(sourceMock);

        FormatArguments formatArguments = new FormatArguments();

        String result = jsonPrettifier.getProcessed(formatArguments);
        assertEquals(formattedJson, result);

        verify(sourceMock).getProcessed(any(FormatArguments.class));
    }
}
