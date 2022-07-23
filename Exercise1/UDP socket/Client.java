import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputSR = null;
        OutputStreamWriter outputSW = null;
        BufferedReader BufferedReaderFromSv = null;
        BufferedWriter bufferedWriterToServer = null;

        try {
            socket = new Socket("localhost", 1234);

            inputSR = new InputStreamReader(socket.getInputStream());
            outputSW = new OutputStreamWriter(socket.getOutputStream());

            BufferedReaderFromSv = new BufferedReader(inputSR);
            bufferedWriterToServer = new BufferedWriter(outputSW);

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String msgSendToServer = scanner.nextLine();
                    bufferedWriterToServer.write(msgSendToServer);
                    bufferedWriterToServer.newLine();
                    bufferedWriterToServer.flush();

                    System.out.println("Server: " + BufferedReaderFromSv.readLine());

                    if (msgSendToServer.equalsIgnoreCase("BYE"))
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
                if (inputSR != null)
                    inputSR.close();
                if (outputSW != null)
                    outputSW.close();
                if (BufferedReaderFromSv != null)
                    BufferedReaderFromSv.close();
                if (bufferedWriterToServer != null)
                    bufferedWriterToServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
