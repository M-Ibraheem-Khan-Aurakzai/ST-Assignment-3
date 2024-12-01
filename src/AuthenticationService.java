import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationService {
    private static final String DB_URL = "jdbc:mysql://mysql-st-a3-st-a-3.b.aivencloud.com:10080/defaultdb";
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = System.getenv("DDB_PASSWORD");

    public String authenticateUser(String email) {
        String userName = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT name FROM User WHERE Email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("Name");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    public boolean authenticateUserPassword(String email, String password) {
        String userName = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Name FROM User WHERE Email = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("Name");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName != null;
    }
}
