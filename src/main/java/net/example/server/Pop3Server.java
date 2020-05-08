package net.example.server;

import net.example.pop3proto.Pop3CommandParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Pop3Server {
    public static void main(String[] args) throws Exception {
        int serverPort = 2101;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        while (true) {
            Socket clientSocket = null;
            String clientIP = null;

            try {
                clientSocket = serverSocket.accept();
                clientIP = clientSocket.getRemoteSocketAddress().toString();
                System.out.println("[" + clientIP + "] connected");

                BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), false);

                Pop3SessionContext sessionContext = new Pop3SessionContext(clientIP);
                Pop3ServerProcessor pop3ServerProcessor = new Pop3ServerProcessor(sessionContext, socketIn, socketOut);
                pop3ServerProcessor.execute();

            } catch (Exception e) {
                if (clientSocket != null) {
                    System.out.println("[" + clientIP + "] " + e.getMessage());
                    clientSocket.close();
                }
            }
        }
    }
}
