import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args)
            throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        // get the localhost IP address
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        // establish socket connection to server
        socket = new Socket(host.getHostName(), 4040);
        // write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");

        // read the server response message
        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();

        // print the message after uppercase
        System.out.println("Server: " + message);

        // close resources
        ois.close();
        oos.close();
        Thread.sleep(100);
    }
}