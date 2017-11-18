package restClient;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerConnection implements Runnable {

    private Socket connectionSocket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String HTTPVersion;

    public ServerConnection(Socket connectionSocket) throws IOException {
        this.connectionSocket = connectionSocket;
        this.inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        this.outToClient = new DataOutputStream(connectionSocket.getOutputStream());
    }

    public void run() {
        try {
            System.out.println("this is a new connection");
            String line = inFromClient.readLine();
            System.out.println(line);
            interpretLine(line);
            connectionSocket.close();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket time out.");
        } catch (IOException e) {
            System.out.println("Unable to read or write to socket.");
        }
    }

    private void interpretLine(String message) throws IOException {
        String[] components = message.split(" ");
        if (checkGetMessageFormat(components)) {
            String queryParameter;
            if (components[1].contains("^/")) {
                queryParameter = components[1].substring(1, components[1].length());
            }

            ////////////// make query here ////////////////
            String json = "JP Morgan";
            outToClient.writeBytes(HTTPVersion + " 200 OK\r\n");
            sendMessage(json);

        } else {
            outToClient.writeBytes("HTTP/1.0 400 Bad Request\r\n");
        }
    }

    private boolean checkGetMessageFormat(String[] components) {
        if (components.length == 3) {
            if (components[0].equals("GET") && (components[2].equals("HTTP/1.1") || components[2].equals("HTTP/1.0"))) {
                HTTPVersion = components[2];
                return true;
            }
        }
        return false;
    }

    private void sendMessage(String json) throws IOException {
        outToClient.writeBytes("\r\n");
        outToClient.writeBytes(json);
    }

    private String getJSON(String identifier) throws IOException {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("text.txt"));
            String jsonString = "";
            while ((jsonString = fileReader.readLine()) != null) {
                if (jsonString.contains("{name: " + identifier)) {
                    return jsonString;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return "";
    }
}
