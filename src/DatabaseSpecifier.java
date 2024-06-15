public class DatabaseSpecifier {
    private final int PORT = 3307;
    private final String HOST = "127.0.0.1";
    private final String USER = "root";
    private final String PASSWORD = "1231";

    private final String className = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/Shop";

    String getURL(){
        return URL;
    }

    String getUsername(){
        return USER;
    }

    String getPassword(){
        return PASSWORD;
    }

    String getClassName(){
        return className;
    }

}
