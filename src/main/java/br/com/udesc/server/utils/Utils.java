package br.com.udesc.server.utils;

import br.com.udesc.server.model.Empresa;
import br.com.udesc.server.model.Pessoa;

/**
 *
 * @author fabio
 */
public class Utils {

    public String padronizaInsercao(String variavel, int tamanho) {
        if (variavel.length() > tamanho) {
            variavel = variavel.substring(0, tamanho);
        }
        variavel = String.format("%" + tamanho + "s", variavel);
        variavel = variavel.replaceAll(" ", "*");

        return variavel;
    }

    public Pessoa converteStringPessoa(String msg) {
        Pessoa pessoa = new Pessoa();

        String nome = msg.substring(18, 117);
        pessoa.setNome(reverteConversao(nome));
//        System.out.println("\nnome: " +pessoa.getNome());

        String cpf = msg.substring(7, 17);
        pessoa.setCpf(reverteConversao(cpf));
//        System.out.println("\ncpf: " +pessoa.getCpf());

        String endereco = msg.substring(118, 217);
        pessoa.setEndereco(reverteConversao(endereco));
//        System.out.println("\nendereço: " +pessoa.getEndereco());

        String cnpjEmpresa = msg.substring(218, 231);
        pessoa.setCnpjEmpresa(reverteConversao(cnpjEmpresa));
//        System.out.println("\ncpg: " +pessoa.getCnpjEmpresa());

        return pessoa;
    }

    public Empresa converteStringEmpresa(String msg) {
        Empresa empresa = new Empresa();

        String nome = msg.substring(21, 120);
        empresa.setNome(reverteConversao(nome));
//        System.out.println("\nnome: " +pessoa.getNome());

        String cpf = msg.substring(7, 20);
        empresa.setCnpj(reverteConversao(cpf));
//        System.out.println("\ncpf: " +pessoa.getCpf());

        String endereco = msg.substring(121, 220);
        empresa.setEndereco(reverteConversao(endereco));
//        System.out.println("\nendereço: " +pessoa.getEndereco());

        String cpfPessoa = msg.substring(221, 232);
        empresa.getPessoas().add(reverteConversao(cpfPessoa));
//        System.out.println("\ncpg: " +pessoa.getCnpjEmpresa());

        return empresa;
    }

    private String reverteConversao(String variavel) {
        return variavel.replaceAll("\\*", " ").trim();
    }
}
