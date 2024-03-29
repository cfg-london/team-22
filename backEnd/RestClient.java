
import java.io.IOException;
import java.net.*;
import java.security.InvalidParameterException;

public class RestClient {

    ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                new RestClient(Integer.parseInt(args[0]));
            } else {
                throw new InvalidParameterException();
            }
        } catch (NumberFormatException | InvalidParameterException e) {
            System.out.println("Usage: java RestClient <host> <ip>");
        } catch (IOException e) {
            System.out.println("Unable to open server socket.");
            e.printStackTrace();
        }
    }

    public RestClient(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        while (!serverSocket.isClosed()) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("New connection accepted...");
            new Thread(new ServerConnection(connectionSocket)).start();
        }
    }
}
