import java.sql.SQLException;

public class Main {
    public static DatabaseHandler dbHandler;

    static {
        try {
            dbHandler = new DatabaseHandler();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

    }
}
