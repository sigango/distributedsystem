import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputSR = null;
        OutputStreamWriter outputSW = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        ServerSocket serverSocket = null;

        while (true) {
            try {
                serverSocket = new ServerSocket(1234);
                socket = serverSocket.accept();
                inputSR = new InputStreamReader(socket.getInputStream());
                outputSW = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputSR);
                bufferedWriter = new BufferedWriter(outputSW);

                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + msgFromClient);
                    bufferedWriter.write("After uppercase: " + msgFromClient.toUpperCase(Locale.ROOT));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgFromClient.equalsIgnoreCase("BYE"))
                        break;
                }
                socket.close();
                serverSocket.close();
                inputSR.close();
                outputSW.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
