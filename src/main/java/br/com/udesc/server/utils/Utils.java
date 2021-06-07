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

        String nome = msg.substring(18, 118);
        pessoa.setNome(reverteConversao(nome));

        String cpf = msg.substring(7, 18);
        pessoa.setCpf(reverteConversao(cpf));

        String endereco = msg.substring(118, 217);
        pessoa.setEndereco(reverteConversao(endereco));

        String cnpjEmpresa = msg.substring(218, 232);
        pessoa.setCnpjEmpresa(reverteConversao(cnpjEmpresa));

        return pessoa;
    }

    public Empresa converteStringEmpresa(String msg) {
        Empresa empresa = new Empresa();

        String nome = msg.substring(21, 120);
        empresa.setNome(reverteConversao(nome));

        String cnpj = msg.substring(7, 20);
        empresa.setCnpj(reverteConversao(cnpj));

        String endereco = msg.substring(121, 220);
        empresa.setEndereco(reverteConversao(endereco));

//        String cpfPessoa = msg.substring(221, 232);
//        empresa.getPessoas().add(reverteConversao(cpfPessoa));
//        System.out.println("\ncpg: " +empresa.getPessoas());

        return empresa;
    }

    public String reverteConversao(String variavel) {
        return variavel.replaceAll("\\*", " ").trim();
    }
    
}
