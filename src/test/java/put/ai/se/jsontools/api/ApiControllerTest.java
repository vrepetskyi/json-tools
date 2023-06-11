package put.ai.se.jsontools.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ApiControllerTest {

    @Mock
    private HttpExchange httpExchange;

    @Test
    void testHandlePostRequest() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Prepare test data
        String requestBody = "Test request body";
        String expectedResponseBody = "Test response body";
        int expectedResponseCode = 200;

        // Mock HttpExchange methods
        when(httpExchange.getRequestHeaders()).thenReturn(new Headers());
        when(httpExchange.getRemoteAddress())
                .thenReturn(new InetSocketAddress(expectedResponseBody, expectedResponseCode));
        when(httpExchange.getRequestMethod()).thenReturn("POST");
        when(httpExchange.getRequestBody()).thenReturn(new ByteArrayInputStream(requestBody.getBytes()));

        // Mock EndpointResolver
        EndpointResolver mockResolver = mock(EndpointResolver.class);
        when(mockResolver.resolve(requestBody)).thenReturn(expectedResponseBody);

        // Mock OutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        // Call the handle() method
        HttpHandler handler = ApiController.createHandler(ApiController.COMPARE_ENDPOINT, mockResolver);
        handler.handle(httpExchange);

        // Verify EndpointResolver method invocation
        verify(mockResolver).resolve(requestBody);

        // Verify HttpExchange method invocations
        verify(httpExchange, atLeastOnce()).getRequestMethod();
        verify(httpExchange).getRequestBody();
        verify(httpExchange).getResponseBody();
        verify(httpExchange).sendResponseHeaders(expectedResponseCode, expectedResponseBody.length());

        // Verify the response body
        String actualResponseBody = outputStream.toString();
        assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    void testHandleNonPostRequest() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Prepare test data
        String requestBody = "Test request body";
        String expectedResponseBody = "Invalid request method";
        int expectedResponseCode = 405;

        // Mock HttpExchange methods
        when(httpExchange.getRequestHeaders()).thenReturn(new Headers());
        when(httpExchange.getRemoteAddress())
                .thenReturn(new InetSocketAddress(expectedResponseBody, expectedResponseCode));
        when(httpExchange.getRequestMethod()).thenReturn("GET");
        when(httpExchange.getRequestBody()).thenReturn(new ByteArrayInputStream(requestBody.getBytes()));

        // Mock OutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(httpExchange.getResponseBody()).thenReturn(outputStream);

        // Call the handle() method
        HttpHandler handler = ApiController.createHandler(ApiController.COMPARE_ENDPOINT, null);
        handler.handle(httpExchange);

        // Verify HttpExchange method invocations
        verify(httpExchange, atLeastOnce()).getRequestMethod();
        verify(httpExchange).getResponseBody();
        verify(httpExchange).sendResponseHeaders(expectedResponseCode, expectedResponseBody.length());

        // Verify the response body
        String actualResponseBody = outputStream.toString();
        assertEquals(expectedResponseBody, actualResponseBody);
    }
}
