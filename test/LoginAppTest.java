import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginAppTest {
    private static final AuthenticationService authService = new AuthenticationService();

    //Test cases for Email Authentication
    //Testing that an authentic email is found and name is returned
    @Test
    public void testValidEmail() {
        String result = authService.authenticateUser("johndoe@example.com");
        assertNotNull(result, "User name should not be null for valid email.");
        assertEquals("Expected user name does not match.","John Doe",  result);
    }

    //Testing that an invalid/non-existing email in database only returns null
    @Test
    public void testInvalidEmail() {
        String result = authService.authenticateUser("nonexistent@example.com");
        Assert.assertNull("Result should be null for non-existent email.", result);
    }

    //Testing that empty field only returns null
    @Test
    public void testEmptyEmailField() {
        String result = authService.authenticateUser("");
        assertNull("Result should be null for empty email.", result);
    }

    //Testing SQL Injection Threats on Email authenticator
    @Test
    public void testEmailSqlInjection() {
        String result = authService.authenticateUser("'; DROP TABLE User; --");
        assertNull("SQL injection attempt should return null.", result);
    }

    //Testing Case sensitivity to check that Uppercase letters do not affect output
    @Test
    public void testEmailCaseSensitivity() {
        String result = authService.authenticateUser("JOHNDOE@example.com");
        Assert.assertNotNull("User name should not be null for valid email even if the email is in upper case.", result);
        assertEquals("Expected user name should not be null.","John Doe",  result);
    }

    //----------Test cases for the new function "authenticateUserPassword"-----------------------------------------
    //Testing that a valid email and valid password always return true
    @Test
    public void testValidPassword() {
        boolean result = authService.authenticateUserPassword("johndoe@example.com", "password123");
        assertTrue("Expected value 'true' for correct email & password did not match.", result);
    }

    //Testing that an invalid password always returns false
    @Test
    public void testInvalidPassword() {
        boolean result = authService.authenticateUserPassword("johndoe@example.com", "password321");
        assertFalse("Expected value 'false' for Invalid password did not match.", result);
    }

    @Test //Testing that empty password field only returns false
    public void testEmptyPasswordField() {
        boolean result = authService.authenticateUserPassword("johndoe@example.com", "");
        assertFalse("Expected value 'false' for null password did not match.", result);
    }

    @Test //Testing SQL Injection Threats in both email & password fields
    public void testPasswordSqlInjection() {
        boolean result = authService.authenticateUserPassword("' OR '1'='1", "' OR '1'='1");
        assertFalse("Expected value 'false' for sql injection in password field did not match.", result);
    }

    @Test //Test case where both email and password fields are empty to ensure the function handles it
    public void testEmptyEmailAndPassword() {
        boolean result = authService.authenticateUserPassword("", "");
        assertFalse("Expected 'false' for both email and password fields being empty.", result);
    }
}
