import java.io.Serial;
import java.io.Serializable;

public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final int time;

    Notification(String message, int time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "message='" + message + '\'' +
                ", time=" + time +
                '}';
    }
}
