package put.ai.se.jsontools.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CompareHandlerTest {

    @Test
    public void testHandle_ValidRequest() throws IOException {
        HttpExchange exchange = mock(HttpExchange.class);
        Headers headers = mock(Headers.class);

        InetAddress localhost = InetAddress.getLocalHost();
        String ipAddress = localhost.getHostAddress();

        when(exchange.getRequestMethod()).thenReturn("POST");
        when(exchange.getRequestHeaders()).thenReturn(headers);
        when(headers.getFirst("X-FORWARDED-FOR")).thenReturn(null);
        when(exchange.getRemoteAddress()).thenReturn(new InetSocketAddress(ipAddress, 8080));

        String reqBody = "{\"string1\": \"hello\", \"string2\": \"world\"}";
        String plainResBody = "[1]";
        int lineNumber = Integer.parseInt(plainResBody.substring(1, plainResBody.length() - 1));
        InputStream inputStream = new ByteArrayInputStream(reqBody.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream(lineNumber);

        when(exchange.getRequestBody()).thenReturn(inputStream);
        when(exchange.getResponseBody()).thenReturn(outputStream);

        CompareHandler.handle(exchange);

        verify(exchange).getRequestMethod();
        verify(exchange.getRequestHeaders()).getFirst("X-FORWARDED-FOR");
        verify(exchange).getRemoteAddress();
        verify(exchange).getRequestBody();
        verify(exchange).getResponseBody();

        String output = outputStream.toString();
        int outputLineNumber = Integer.parseInt(output.substring(1, output.length() - 1));
        int expectedLineNumber = 1;
        assertEquals(expectedLineNumber, outputLineNumber);
    }
}
