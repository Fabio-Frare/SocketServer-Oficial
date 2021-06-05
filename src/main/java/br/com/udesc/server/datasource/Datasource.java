package br.com.udesc.server.datasource;

import br.com.udecs.server.Server;
import br.com.udesc.server.model.Empresa;
import br.com.udesc.server.model.Pessoa;
import br.com.udesc.server.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabio
 */
public class Datasource {

    private Utils utils;
    private Pessoa pessoa;
    private Empresa empresa;
    private List<Pessoa> dadosPessoas = new ArrayList<>();
    private List<Empresa> dadosEmpresas = new ArrayList();

    public Datasource() {
        metodoCriarObjetos();
    }

    public void addPessoa(String msg) {
//        System.out.println("Datasource pessoa:" + msg);
        this.pessoa = new Pessoa();
        utils = new Utils();

        pessoa = utils.converteStringPessoa(msg);
        dadosPessoas.add(pessoa);
//        ListaEmpresas();
//        ListaPessoas();
    }

    public void addEmpresa(String msg) {
        this.empresa = new Empresa();
        utils = new Utils();

        empresa = utils.converteStringEmpresa(msg);
        dadosEmpresas.add(empresa);
    }

    public List<Pessoa> getDadosPessoas() {
        return dadosPessoas;
    }

    public void setDadosPessoas(List<Pessoa> dadosPessoas) {
        this.dadosPessoas = dadosPessoas;
    }

    public List<Empresa> getDadosEmpresas() {
        return dadosEmpresas;
    }

    public void setDadosEmpresas(List<Empresa> dadosEmpresas) {
        this.dadosEmpresas = dadosEmpresas;
    }

    public String ListaPessoas() {
        String pessoas = "";
        for (var pessoa : dadosPessoas) {
            pessoas += "Nome: "+ pessoa.getNome() + " CPF: " + pessoa.getCpf() + " || ";
        }
        return pessoas;
    }

    public String ListaEmpresas() {
        String empresas = "";
        for (var empresa : dadosEmpresas) {
            empresas += "Nome: " + empresa.getNome() + " CNPJ: " + empresa.getCnpj() + " || ";
        }
        return empresas;
    }

    public void metodoCriarObjetos() {
        Empresa empresa = new Empresa();
        empresa.setNome("Singular Sistemas");
        empresa.setCnpj("12345678000101");

        Empresa empresa2 = new Empresa();
        empresa2.setNome("Dalila Têxtil");
        empresa2.setCnpj("99999999999999");

//        System.out.println("metodo criar empresa: ");
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fábio Frare");
        pessoa.setCpf("04839577777");
        pessoa.setEndereco("Rua 7 de setembro");
        pessoa.setCnpjEmpresa(empresa.getCnpj());
//        empresa.getPessoas().add(pessoa.getCpf());

        dadosPessoas.add(pessoa);
        dadosEmpresas.add(empresa);
        dadosEmpresas.add(empresa2);

    }
}
