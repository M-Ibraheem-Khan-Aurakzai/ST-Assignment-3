import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginAppTest {
    // Create an instance of the Authenticator class for testing
    private final LoginApp.Authenticator authenticator = new LoginApp.Authenticator();

    // Test cases for Email Authentication
    @Test // Testing that a valid email existing in database returns the valid name of the email
    void testValidEmail() {
        String result = authenticator.authenticateUser("johndoe@example.com");
        assertNotNull(result, "User name should not be null for valid email.");
        assertEquals("John Doe", result, "Expected user name does not match.");
    }

    @Test // Testing that an invalid/non-existing email in database only returns null
    void testInvalidEmail() {
        String result = authenticator.authenticateUser("nonexistent@example.com");
        assertNull(result, "Result should be null for non-existent email.");
    }

    @Test // Testing that empty field only returns null
    void testEmptyEmailField() {
        String result = authenticator.authenticateUser("");
        assertNull(result, "Result should be null for empty email.");
    }

    @Test // Testing SQL Injection Threats
    void testEmailSqlInjection() {
        String result = authenticator.authenticateUser("'; DROP TABLE User; --");
        assertNull(result, "SQL injection attempt should return null.");
    }

    @Test // Testing Case sensitivity to check that Uppercase letters do not affect output
    void testEmailCaseSensitivity() {
        String result = authenticator.authenticateUser("JOHNDOE@example.com");
        assertNotNull(result, "User name should not be null for valid email even if the email is in upper case.");
        assertEquals("John Doe", result, "Expected user name should not be null.");
    }
}
