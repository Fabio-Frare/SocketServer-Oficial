package br.com.udecs.server;

import br.com.udesc.server.datasource.Datasource;
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

    private static Socket s;
    private static ServerSocket ss;
    private static Datasource datasource;

    public static void main(String[] args) throws IOException {
        ss = new ServerSocket(80);
        ss.setReuseAddress(true);

        while (true) {

            receberDados();
            enviarDados();

        }
    }


    public static void receberDados() throws IOException {
        datasource = new Datasource();
        s = ss.accept();
        System.out.println("Cliente connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        
        System.out.println("Client: " + str);
    }

    public static void enviarDados() throws IOException {
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("yes");
        pr.flush();
    }

}
