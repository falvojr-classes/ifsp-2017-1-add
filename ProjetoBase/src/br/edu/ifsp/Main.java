package br.edu.ifsp;

import br.edu.ifsp.controller.PessoaController;
import br.edu.ifsp.model.Contato;
import br.edu.ifsp.model.PessoaFisica;
import br.edu.ifsp.model.PessoaJuridica;
import java.sql.SQLException;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Classe principal para testes.
 * 
 * @author falvojr
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        PessoaController controller = PessoaController.getInstancia();
        
        PessoaFisica pf = new PessoaFisica();
        pf.setNome("Venilton");
        pf.setCpf("999.999.999-99");
        pf.setTelefone("(16) 99721-8281");
        
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome("Geraldo");
        pj.setCnpj("888.999.999/9999-99");
        pj.setTelefone("(16) 99721-8281");
        
        controller.inserir(pf);
        controller.inserir(pj);
        
//        String msgInserir = String.format("Contato %d inserido!", entidade.getId());
//        System.out.println(msgInserir);
//        
//        entidade.setNome("Contato Alterado");
//        entidade.setEmail("");
//        controller.alterar(entidade);
//        
//        String msgAlterar = String.format("Contato %d alterado!", entidade.getId());
//        System.out.println(msgAlterar);
//        
//        controller.deletar(entidade);
//        
//        String msgDeletar = String.format("Contato %d deletado!", entidade.getId());
//        System.out.println(msgDeletar);
//        
//        List<Contato> lista = controller.listar();
//        
//        String msgListar = String.format("Contatos existentes: %d", lista.size());
//        System.out.println(msgListar);
//        
//        for (Contato contato : lista) {
//            System.out.println(contato.getNome());
//        }
    }
    
}
