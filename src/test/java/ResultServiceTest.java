import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultServiceTest {

    @Test
    void getDatabaseHostReturnsDefaultWhenEnvMissing() throws Exception {
        Method getDatabaseHost = ResultService.class.getDeclaredMethod("getDatabaseHost");
        getDatabaseHost.setAccessible(true);

        String host = (String) getDatabaseHost.invoke(null);
        assertTrue(host != null && !host.isBlank());
    }

    @Test
    void getDatabaseUrlContainsExpectedPieces() throws Exception {
        Method getDatabaseUrl = ResultService.class.getDeclaredMethod("getDatabaseUrl");
        getDatabaseUrl.setAccessible(true);

        String url = (String) getDatabaseUrl.invoke(null);

        assertTrue(url.startsWith("jdbc:mariadb://"));
        assertTrue(url.contains(":3306/calc_data"));
        assertTrue(url.contains("useSSL=false"));
        assertTrue(url.contains("allowPublicKeyRetrieval=true"));
        assertTrue(url.contains("serverTimezone=UTC"));
    }
}
