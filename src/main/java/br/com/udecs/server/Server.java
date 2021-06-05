package br.com.udecs.server;

import br.com.udesc.server.datasource.Datasource;
import br.com.udesc.server.model.Empresa;
import br.com.udesc.server.model.Pessoa;
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

        metodoCriarObjetos();

        while (true) {

            receberDados();
//            enviarDados();

        }
    }

    public static void receberDados() throws IOException {

        s = ss.accept();
        System.out.println("Cliente connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        trataDados(str);

//        System.out.println("str.lenght: " + str.length());
//        System.out.println("Client: " + str);
    }

    public static void enviarDados(String msg) throws IOException {
        pr = new PrintWriter(s.getOutputStream());
        pr.println(msg);
        System.out.println("enviar dados " + msg);
        pr.flush();
    }

    public static void trataDados(String msg) throws IOException {
        String entidade = msg.substring(0, 1);
        String operacao = msg.substring(1, 7);

        System.out.println("msg " + msg);
//        System.out.println("entidade " + entidade);
//        System.out.println("operacao " + operacao);

        switch (operacao) {
            case "INSERT":
                if (entidade.equalsIgnoreCase("1")) {
                    datasource.addPessoa(msg);
                } else {
                    datasource.addEmpresa(msg);
                }
                break;
            case "UPDATE":
                if (entidade.equalsIgnoreCase("1")) {
                    datasource.addPessoa(msg);
                } else {
                    datasource.addEmpresa(msg);
                }
                break;
            case "GET***":
                if (entidade.equalsIgnoreCase("1")) {
                    datasource.addPessoa(msg);
                } else {
                    datasource.addEmpresa(msg);
                }
                break;
            case "DELETE":
                if (entidade.equalsIgnoreCase("1")) {
                    datasource.addPessoa(msg);
                } else {
                    datasource.addEmpresa(msg);
                }
                break;
            case "LIST**":
                if (entidade.equalsIgnoreCase("1")) {
                    msg = datasource.ListaPessoas();
                    System.out.println("entrou switch case listar pessoa" + msg);
                    enviarDados(msg);
//                    System.out.println("saiu switch case listar pessoa" + msg);
                    receberDados();
                } else {
                    msg = datasource.ListaEmpresas();
                    System.out.println("entrou switch case listar empresa" + msg);
                    enviarDados(msg);
//                    System.out.println("saiu switch case listar empresa" + msg);
                    receberDados();
                }

                break;
            default:
                System.out.println("Default switch case server.");
                break;

        }

    }

    public static void metodoCriarObjetos() {
        Empresa empresa = new Empresa();
        empresa.setNome("Singular Sistemas");
        empresa.setCnpj("12345678000101");

        Empresa empresa2 = new Empresa();
        empresa2.setNome("Dalila Têxtil");
        empresa2.setCnpj("99999999999999");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fábio Frare");
        pessoa.setCpf("04839577777");
        pessoa.setEndereco("Rua 7 de setembro");
        pessoa.setCnpjEmpresa(empresa.getCnpj());

        Datasource banco = new Datasource();
        banco.getDadosEmpresas().add(empresa);
        banco.getDadosEmpresas().add(empresa2);
        banco.getDadosPessoas().add(pessoa);
//        banco.ListaEmpresas();
//        banco.getDadosEmpresas().forEach(System.out::println);
    }
}
