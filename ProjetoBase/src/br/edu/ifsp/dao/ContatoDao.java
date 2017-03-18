package br.edu.ifsp.dao;

import java.util.List;

import br.edu.ifsp.model.Contato;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de persistência para a entidade Contato. Essa classe implementa os
 * padrões Singleton e Strategy.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public class ContatoDao extends BaseDao implements IContatoDao {

    /* INICIO Singleton "Preguicoso" */
    
    private static ContatoDao instancia;

    public static ContatoDao getInstancia() {
        if (ContatoDao.instancia == null) {
            ContatoDao.instancia = new ContatoDao();
        }
        return ContatoDao.instancia;
    }

    private ContatoDao() {
        super();
    }

    /* FIM Singleton "Preguicoso" */
    
    @Override
    public void inserir(Contato entidade) {
        String sql = "INSERT INTO contatos "
                + "(nome, email, telefone, data_nascimento)  "
                + "VALUES  "
                + "(?, ?, ?, ?);";
        try {
            PreparedStatement comando = super.getConexao().prepareStatement(sql);
            
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void alterar(Contato entidade) {
 
    }

    @Override
    public void deletar(Contato entidade) {

    }

    @Override
    public List<Contato> listar() {
        return null;
    }

}
