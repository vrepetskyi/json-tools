package put.ai.se.jsontools.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatResolverTest {
    @Mock
    private FormatRequest formatRequest;

    private FormatResolver formatResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        formatResolver = new FormatResolver();
    }

    @Test
    void resolve_MissingSourceKey_ThrowsIllegalArgumentException() {
        String plainReqBody = "{ \"arguments\": {} }";

        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> formatResolver.resolve(plainReqBody));

        assertEquals("Key \"source\" is required and should be an object", exception.getMessage());
    }

    @Test
    void resolve_MissingArgumentsKey_ThrowsIllegalArgumentException() {
        String plainReqBody = "{ \"source\": { \"key\": \"value\" } }";

        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> formatResolver.resolve(plainReqBody));

        assertEquals("Key \"arguments\" is required and should be an object", exception.getMessage());
    }

    @Test
    void resolve_NullRequest_ThrowsIllegalArgumentException() {
        String plainReqBody = null;

        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> formatResolver.resolve(plainReqBody));

        assertEquals("Invalid request format. See documentation", exception.getMessage());
    }
}
