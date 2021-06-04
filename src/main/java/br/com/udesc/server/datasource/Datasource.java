package br.com.udesc.server.datasource;

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
    private List<Pessoa>  dadosPessoas  = new ArrayList<>();
    private List<Empresa> dadosEmpresas = new ArrayList();
    
    
    public void addPessoa(String msg) {
//        System.out.println("Datasource pessoa:" + msg);
        this.pessoa = new Pessoa();
        utils       = new Utils();
        
        pessoa = utils.converteStringPessoa(msg);
        dadosPessoas.add(pessoa);
        ListaEmpresas();
        ListaPessoas();
    }
    
    public void addEmpresa(String empresa) {
  
        System.out.println("Empresa add datasource: ");
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
    
    
    public void ListaPessoas() {
        for (Pessoa pessoa : dadosPessoas) {
            System.out.println("Pessoas: " + pessoa.getNome());
        }
    }

    public String ListaEmpresas() {
        String empresas ="";
        for(int i = 0; i < dadosEmpresas.size(); i++) {
            empresas += utils.padronizaInsercao(dadosEmpresas.get(i).getNome(), 100);
        }        
        return empresas;
        
    }
}
