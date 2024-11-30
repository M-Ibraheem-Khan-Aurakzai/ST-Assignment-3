import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginAppTest {
    //Test cases for Email Authentication
    @org.junit.jupiter.api.Test //Testing that a valid email existing in database returns the valid name of the email
    void testValidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("johndoe@example.com");
        assertNotNull(result, "User name should not be null for valid email.");
        assertEquals("John Doe", result, "Expected user name does not match.");
    }

    @Test //Testing that an invalid/non-existing email in database only returns null
    void testInvalidEmail() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("nonexistent@example.com");
        assertNull(result, "Result should be null for non-existent email.");
    }

    @Test //Testing that empty field only returns null
    void testEmptyEmailField() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("");
        assertNull(result, "Result should be null for empty email.");
    }

    @Test //Testing SQL Injection Threats
    void testEmailSqlInjection() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("'; DROP TABLE User; --");
        assertNull(result, "SQL injection attempt should return null.");
    }

    @Test //Testing Case sensitivity to check that Uppercase letters do not affect output
    void testEmailCaseSensitivity() {
        LoginApp app = new LoginApp();
        String result = app.authenticateUser("JOHNDOE@example.com");
        assertNotNull(result, "User name should not be null for valid email even if the email is in upper case.");
        assertEquals("John Doe", result, "Expected user name should not be null.");
    }

    //----------Test cases for the new function "authenticateUserPassword"-----------------------------------------
//    @Test //Testing that a valid email and valid password always return true
//    void testValidPassword() {
//        LoginApp app = new LoginApp();
//        boolean result = app.authenticateUserPassword("johndoe@example.com", "password123");
//        assertTrue(result, "Expected value 'true' for correct email & password did not match.");
//    }
//
//    @Test //Testing that an invalid password always returns false
//    void testInvalidPassword() {
//        LoginApp app = new LoginApp();
//        boolean result = app.authenticateUserPassword("johndoe@example.com", "password321");
//        assertFalse(result, "Expected value 'false' for Invalid password did not match.");
//    }
//
//    @Test //Testing that empty password field only returns false
//    void testEmptyPasswordField() {
//        LoginApp app = new LoginApp();
//        boolean result = app.authenticateUserPassword("johndoe@example.com", "");
//        assertFalse(result, "Expected value 'false' for null password did not match.");
//    }
//
//    @Test //Testing SQL Injection Threats in both email & password fields
//    void testPasswordSqlInjection() {
//        LoginApp app = new LoginApp();
//        boolean result = app.authenticateUserPassword("' OR '1'='1", "' OR '1'='1");
//        assertFalse(result, "Expected value 'false' for sql injection in password field did not match.");
//    }
//
//    @Test //Test case where both email and password fields are empty to ensure the function handles it
//    void testEmptyEmailAndPassword() {
//        LoginApp app = new LoginApp();
//        boolean result = app.authenticateUserPassword("", "");
//        assertFalse(result, "Expected 'false' for both email and password fields being empty.");
//    }

}