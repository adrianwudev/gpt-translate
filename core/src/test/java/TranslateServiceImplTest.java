import com.adrianwudev.TranslateService;
import com.adrianwudev.TranslateServiceImpl;
import com.adrianwudev.config.ChatGPTConfig;
import com.adrianwudev.mocks.ChatGPTServiceMockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslateServiceImplTest {
    private TranslateService translateService;

    @BeforeEach
    void setUp() {
        translateService = new TranslateServiceImpl(new ChatGPTServiceMockImpl(new ChatGPTConfig("model", 0.5)));
    }

    @Test
    void testTranslate_withEmptySourceAndLang_shouldReturnWarningMessage() {
        String actualTranslation = translateService.translate("", "");
        assertEquals("please enter the valid source and language which you want to translate to", actualTranslation);
    }

    @Test
    void testTranslate_withInvalidLang_shouldReturnWarningMessage() {
        String actualTranslation = translateService.translate("Hello", "");
        assertEquals("please enter the valid source and language which you want to translate to", actualTranslation);
    }

    @Test
    void testTranslate_withNullSourceAndLang_shouldThrowException() {
        assertThrows(NullPointerException.class, () -> translateService.translate(null, "en"));
        assertThrows(NullPointerException.class, () -> translateService.translate("", null));
        assertThrows(NullPointerException.class, () -> translateService.translate(null, null));
    }
}