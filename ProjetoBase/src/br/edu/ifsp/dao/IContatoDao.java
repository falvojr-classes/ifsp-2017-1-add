package br.edu.ifsp.dao;

import java.util.List;

import br.edu.ifsp.model.Contato;

/**
 * Strategy utilizada pelo ContatoDao.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public interface IContatoDao {

    public void inserir(Contato entidade);

    public void alterar(Contato entidade);

    public void deletar(Contato entidade);

    public List<Contato> listar();
}
