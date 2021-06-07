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
    private static Datasource datasource = new Datasource();
    private static PrintWriter pr;

    public static void main(String[] args) throws IOException {
        ss = new ServerSocket(80);
        ss.setReuseAddress(true);

        while (true) {
            receberDados();
        }
        
    }

    public static void receberDados() throws IOException {
        s = ss.accept();
        System.out.println("Cliente connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        trataDados(str);
    }

    public static void enviarDados(String msg) throws IOException {
        pr = new PrintWriter(s.getOutputStream());
        pr.println(msg);
        pr.flush();
    }

    public static void trataDados(String msg) throws IOException {
        String entidade = msg.substring(0, 1);
        String operacao = msg.substring(1, 7);

        System.out.println("msg " + msg);
        System.out.println("entidade " + entidade);
        System.out.println("operacao " + operacao);

        switch (operacao) {
            case "INSERT":
                if (entidade.equalsIgnoreCase("1")) {
                    msg = datasource.addPessoa(msg);
                    enviarDados(msg);
                }
                if (entidade.equalsIgnoreCase("2")) {
                    msg = datasource.addEmpresa(msg);
                    enviarDados(msg);
                }
                break;
            case "UPDATE":
                if (entidade.equalsIgnoreCase("1")) {
                    msg = datasource.atualizaPessoa(msg);
                    enviarDados(msg);
                }
                if (entidade.equalsIgnoreCase("2")) {
                    msg = datasource.atualizaEmpresa(msg);
                    enviarDados(msg);
                }
                break;
            case "GET***":
                if (entidade.equalsIgnoreCase("1")) {
                    String cpf = msg.substring(7, msg.length());
                    msg = datasource.buscaPessoa(cpf);
                    enviarDados(msg);
                }
                if (entidade.equalsIgnoreCase("2")) {
                    String cnpj = msg.substring(7, msg.length());
                    msg = datasource.buscaEmpresa(cnpj);
                    enviarDados(msg);
                }
                break;
            case "DELETE":
                if (entidade.equalsIgnoreCase("1")) {
                    String cpf = msg.substring(7, msg.length());
                    msg = datasource.deletaPessoa(cpf);
                    enviarDados(msg);
                }
                if (entidade.equalsIgnoreCase("2")) {
                    String cnpj = msg.substring(7, msg.length());
                    System.out.println("CNPJ: " + cnpj);
                    msg = datasource.deletaEmpresa(cnpj);
                    enviarDados(msg);
                }
                break;
            case "LIST**":
                if (entidade.equalsIgnoreCase("1")) {
                    msg = datasource.ListaPessoas();
                    enviarDados(msg);
                    receberDados();
                }
                if (entidade.equalsIgnoreCase("2")) {
                    msg = datasource.ListaEmpresas();
                    enviarDados(msg);
                    receberDados();
                }
                break;
            default:
                System.out.println("Default switch case server.");
                break;
        }
    }

}
