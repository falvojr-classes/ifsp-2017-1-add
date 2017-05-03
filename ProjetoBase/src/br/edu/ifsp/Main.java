package br.edu.ifsp;

import br.edu.ifsp.controller.ContatoController;
import br.edu.ifsp.model.Contato;
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
        ContatoController controller = ContatoController.getInstancia();
        
        Contato entidade = new Contato();
        entidade.setNome("a");
        entidade.setEmail("");
        entidade.setTelefone("");
        entidade.setDataNascimento(new DateTime());
        
        controller.inserir(entidade);
        
        String msgInserir = String.format("Contato %d inserido!", entidade.getId());
        System.out.println(msgInserir);
        
        entidade.setNome("Contato Alterado");
        entidade.setEmail("");
        controller.alterar(entidade);
        
        String msgAlterar = String.format("Contato %d alterado!", entidade.getId());
        System.out.println(msgAlterar);
        
        controller.deletar(entidade);
        
        String msgDeletar = String.format("Contato %d deletado!", entidade.getId());
        System.out.println(msgDeletar);
        
        List<Contato> lista = controller.listar();
        
        String msgListar = String.format("Contatos existentes: %d", lista.size());
        System.out.println(msgListar);
        
        for (Contato contato : lista) {
            System.out.println(contato.getNome());
        }
    }
    
}
