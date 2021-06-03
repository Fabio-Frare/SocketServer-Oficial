package br.com.udecs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fabio
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(80);
        ss.setReuseAddress(true);
        while (true) {
            Socket s = ss.accept();
            System.out.println("Cliente connected");

            // Recebendo dados  Cliente >> Servidor
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            String str = bf.readLine();
            System.out.println("Client: " + str);

            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println("yes");
            pr.flush();

        }
    }

}
