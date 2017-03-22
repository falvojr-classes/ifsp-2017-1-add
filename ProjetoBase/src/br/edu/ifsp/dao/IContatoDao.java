package br.edu.ifsp.dao;

import java.util.List;

import br.edu.ifsp.model.Contato;
import java.sql.SQLException;

/**
 * Strategy utilizada pelo ContatoDao.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public interface IContatoDao {

    public void inserir(Contato entidade) throws SQLException ;

    public void alterar(Contato entidade) throws SQLException ;

    public void deletar(Contato entidade) throws SQLException ;

    public List<Contato> listar() throws SQLException ;
}
