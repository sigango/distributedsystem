import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    // static ServerSocket variable
    private static ServerSocket server;
    // socket server port
    private static int port = 4040;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        // creating the socket server object
        server = new ServerSocket(port);

        while (true) {
            System.out.println("Waiting for the client request");
            // creating socket and waiting for client connection
            Socket socket = server.accept();
            // read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            // create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // write object to Socket
            oos.writeObject("Message after uppercase: " + message.toUpperCase(Locale.ROOT));

            // close resources
            ois.close();
            oos.close();
            socket.close();
            // terminate the server if client sends exit request
            if (message.equalsIgnoreCase("exit"))
                break;
        }
        System.out.println("Shutting down Socket server!!");
        // close the ServerSocket object
        server.close();
    }

}