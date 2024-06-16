import java.sql.*;

public class DatabaseHandler {
    private final int PORT = 3333;
    private final String HOST = "127.0.0.1";
    private final String USER = "root";
    private final String PASSWORD = "root";

    private final String className = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/Shop";

    private final Statement stmt;

    DatabaseHandler() throws SQLException, ClassNotFoundException {
        Class.forName(className);
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = conn.createStatement();
    }

    ResultSet getData(String sql) throws SQLException {
        ResultSet resultSet;

        resultSet = stmt.executeQuery(sql);

        return resultSet;
    }

    String getURL() {
        return URL;
    }

    String getUsername() {
        return USER;
    }

    String getPassword() {
        return PASSWORD;
    }

    String getClassName() {
        return className;
    }

}
