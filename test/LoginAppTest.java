import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

public class LoginAppTest {
    private static final AuthenticationService authService = new AuthenticationService();

    @Test
    public void testValidEmail() {
        String result = authService.authenticateUser("johndoe@example.com");
        assertNotNull(result, "User name should not be null for valid email.");
        assertEquals("Expected user name does not match.","John Doe",  result);
    }

    @Test
    public void testInvalidEmail() {
        String result = authService.authenticateUser("nonexistent@example.com");
        Assert.assertNull("Result should be null for non-existent email.", result);
    }

    @Test
    public void testEmptyEmailField() {
        String result = authService.authenticateUser("");
        assertNull("Result should be null for empty email.", result);
    }

    @Test
    public void testEmailSqlInjection() {
        String result = authService.authenticateUser("'; DROP TABLE User; --");
        assertNull("SQL injection attempt should return null.", result);
    }

    @Test
    public void testEmailCaseSensitivity() {
        String result = authService.authenticateUser("JOHNDOE@example.com");
        Assert.assertNotNull("User name should not be null for valid email even if the email is in upper case.", result);
        assertEquals("Expected user name should not be null.","John Doe",  result);
    }
}
