package put.ai.se.jsontools.api;

import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;

import put.ai.se.jsontools.core.JsonFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.*;
import static org.mockito.Mockito.*;

public class FormatHandlerTest {
    HttpExchange httpExchange;
    InputStream requestBody;
    OutputStream responseBody;

    @BeforeEach
    void setUp() {
        httpExchange = mock(HttpExchange.class);
        // incoming data
        requestBody = new ByteArrayInputStream("Test request".getBytes());
        // outgoing data
        responseBody = new ByteArrayOutputStream();
        when(httpExchange.getRequestBody()).thenReturn(requestBody);
        when(httpExchange.getResponseBody()).thenReturn(responseBody);
    }


    @Test // In handleTest_NotPost we test the behavior of the FormatHandler when the request method is not POST.
    public void handleTest_NotPost() throws IOException {
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        FormatHandler.handle(httpExchange);
        verify(httpExchange).sendResponseHeaders(405, -1);
    } // 405 - The request method is known by the server but is not supported by the target resource.

    @Test // test, we are simulating the scenario where the request body isn't valid JSON format
    public void handleTest_IllegalRequestFormat() throws IOException {
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        requestBody = new ByteArrayInputStream("not a valid json".getBytes());
        when(httpExchange.getRequestBody()).thenReturn(requestBody);
        assertThrows(JsonSyntaxException.class, () -> FormatHandler.handle(httpExchange));
    }

    @Test
    public void handleTest_CorrectRequestFormat() throws IOException {
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        requestBody = new ByteArrayInputStream("{\"source\": {\"key\": \"value\"}, \"params\": {}}".getBytes());
        when(httpExchange.getRequestBody()).thenReturn(requestBody);
        FormatHandler.handle(httpExchange);
        verify(httpExchange).sendResponseHeaders(eq(200), anyLong());
    }

@Test // This method tests the scenario where the JsonFormatter.format() method throws an IllegalArgumentException.
public void handleTest_FormatIllegalArgumentException() throws IOException {
    when(httpExchange.getRequestMethod()).thenReturn("POST");
    requestBody = new ByteArrayInputStream("{\"source\": {\"key\": \"value\"}, \"params\": {}}".getBytes());
    when(httpExchange.getRequestBody()).thenReturn(requestBody);

    try (MockedStatic<JsonFormatter> mockFormatter = Mockito.mockStatic(JsonFormatter.class)) {
        mockFormatter.when(() -> JsonFormatter.format(anyString(), any()))
                .thenThrow(new IllegalArgumentException("Test Exception"));

        FormatHandler.handle(httpExchange);
    }

    verify(httpExchange).sendResponseHeaders(eq(400), anyLong());
}

@Test // This method tests the scenario where JsonFormatter.format() throws an unexpected RuntimeException. 
public void handleTest_FormatUnexpectedException() throws IOException {
    when(httpExchange.getRequestMethod()).thenReturn("POST");
    requestBody = new ByteArrayInputStream("{\"source\": {\"key\": \"value\"}, \"params\": {}}".getBytes());
    when(httpExchange.getRequestBody()).thenReturn(requestBody);

    try (MockedStatic<JsonFormatter> mockFormatter = Mockito.mockStatic(JsonFormatter.class)) {
        mockFormatter.when(() -> JsonFormatter.format(anyString(), any()))
                .thenThrow(new RuntimeException());

        FormatHandler.handle(httpExchange);
    }

    verify(httpExchange).sendResponseHeaders(eq(500), anyLong());
}


}

