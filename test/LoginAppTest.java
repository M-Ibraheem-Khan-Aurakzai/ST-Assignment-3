import org.junit.Test; // Import JUnit 4 annotation

import static org.junit.Assert.*; // import for assertion methods

public class LoginAppTest {

    // Run before all tests to set the system property for headless mode
    @BeforeAll
    static void setUp() {
        // Set the system property to run in headless mode
        System.setProperty("java.awt.headless", "true");
    }

    // Test cases for Email Authentication

    @Test // Testing that a valid email existing in the database returns the valid name of the email
    public void testValidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com");
        assertNotNull("User name should not be null for valid email.", result);
        assertEquals("Expected user name does not match.", "John Doe", result);
    }

    @Test // Testing that an invalid/non-existing email in the database only returns null
    public void testInvalidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("nonexistent@example.com");
        assertNull("Result should be null for non-existent email.", result);
    }

    @Test // Testing that an empty field only returns null
    public void testEmptyEmailField() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("");
        assertNull("Result should be null for empty email.", result);
    }

    @Test // Testing SQL Injection threats
    public void testEmailSqlInjection() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("'; DROP TABLE User; --");
        assertNull("SQL injection attempt should return null.", result);
    }

    @Test // Testing case sensitivity to check that uppercase letters do not affect output
    public void testEmailCaseSensitivity() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("JOHNDOE@example.com");
        assertNotNull("User name should not be null for valid email even if the email is in upper case.", result);
        assertEquals("Expected user name does not match.", "John Doe", result);
    }
}
