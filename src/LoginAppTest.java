import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAppTest {

    // Test cases for Email Authentication

    @Test // Testing that a valid email existing in the database returns the valid name of the email
    public void testValidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com");
        assertNotNull(result, "User name should not be null for valid email.");
        assertEquals("John Doe", result, "Expected user name does not match.");
    }

    @Test // Testing that an invalid/non-existing email in the database only returns null
    public void testInvalidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("nonexistent@example.com");
        assertNull(result, "Result should be null for non-existent email.");
    }

    @Test // Testing that an empty field only returns null
    public void testEmptyEmailField() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("");
        assertNull(result, "Result should be null for empty email.");
    }

    @Test // Testing SQL Injection threats
    public void testEmailSqlInjection() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("'; DROP TABLE User; --");
        assertNull(result, "SQL injection attempt should return null.");
    }

    @Test // Testing case sensitivity to check that uppercase letters do not affect output
    public void testEmailCaseSensitivity() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("JOHNDOE@example.com");
        assertNotNull(result, "User name should not be null for valid email even if the email is in upper case.");
        assertEquals("John Doe", result, "Expected user name does not match.");
    }

    // You can extend this further by adding other test methods for password-related functionality
}
