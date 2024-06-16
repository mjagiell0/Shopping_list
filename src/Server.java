import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.sql.*;

public class Server {
    private static final int PORT = 2222;
    private static final ConcurrentLinkedDeque<Notification> notificationQueue = new ConcurrentLinkedDeque<>();
    public static DatabaseHandler dbHandler;

    static {
        try {
            dbHandler = new DatabaseHandler();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public final static Shop SHOP;

    static {
        try {
            SHOP = new Shop();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running. Listening on the port " + PORT + "..");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New connection: " + clientSocket);

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            new Thread(clientHandler).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {

                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                while (true) {
                    Notification notification = (Notification) inFromClient.readObject();
                    System.out.println("Notification received from client: " + notification);

                    notificationQueue.add(notification);

                    outToClient.writeObject(notification);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
