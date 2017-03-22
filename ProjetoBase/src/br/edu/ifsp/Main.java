package br.edu.ifsp;

import br.edu.ifsp.dao.ContatoDao;
import br.edu.ifsp.dao.IContatoDao;
import br.edu.ifsp.model.Contato;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Classe principal para testes.
 * 
 * @author falvojr
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        IContatoDao dao = ContatoDao.getInstancia();
        
        Contato entidade = new Contato();
        entidade.setNome("Venilton 2");
        entidade.setEmail("venilton.junior@ifsp.edu.br");
        entidade.setTelefone("16 99721-8281");
        entidade.setDataNascimento(Calendar.getInstance());
        
        dao.inserir(entidade);
        
        String msgInserir = String.format("Contato %d inserido!", entidade.getId());
        System.out.println(msgInserir);
        
        entidade.setNome("Contato Alterado");
        entidade.setEmail("");
        dao.alterar(entidade);
        
        String msgAlterar = String.format("Contato %d alterado!", entidade.getId());
        System.out.println(msgAlterar);
        
        dao.deletar(entidade);
        
        String msgDeletar = String.format("Contato %d deletado!", entidade.getId());
        System.out.println(msgDeletar);
        
        List<Contato> lista = dao.listar();
        
        String msgListar = String.format("Contatos existentes: %d", lista.size());
        System.out.println(msgListar);
        
        for (Contato contato : lista) {
            System.out.println(contato.getNome());
        }
    }
    
}
