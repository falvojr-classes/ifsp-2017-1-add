package br.edu.ifsp.controller;

import br.edu.ifsp.dao.ContatoDao;
import br.edu.ifsp.model.Contato;
import java.util.List;
import br.edu.ifsp.util.ICrud;
import java.sql.SQLException;

/**
 *
 * @author falvojr
 */
public final class ContatoController implements ICrud<Contato>{
    
    /* INICIO Singleton "Preguicoso" */
    
    private static ContatoController instancia;

    public static ContatoController getInstancia() {
        if (instancia == null) {
            instancia = new ContatoController();
        }
        return instancia;
    }
    
    private ContatoController() {
        super();
    }
    
    /* FIM Singleton "Preguicoso" */

    @Override
    public void inserir(Contato entidade) {
        try {
            ContatoDao.getInstancia().inserir(entidade);
        } catch (SQLException sqlException) {
            //TODO Tratar excecao especifica.
        } catch (Exception exception) {
            //TODO Tratar excecao generica.
        }
    }

    @Override
    public void alterar(Contato entidade) {
        try {
            ContatoDao.getInstancia().alterar(entidade);
        } catch (SQLException sqlException) {
            //TODO Tratar excecao especifica.
        } catch (Exception exception) {
            //TODO Tratar excecao generica.
        }
    }

    @Override
    public void deletar(Contato entidade) {
        try {
            ContatoDao.getInstancia().deletar(entidade);
        } catch (SQLException sqlException) {
            //TODO Tratar excecao especifica.
        } catch (Exception exception) {
            //TODO Tratar excecao generica.
        }       
    }

    @Override
    public List<Contato> listar() {
        List<Contato> retorno = null;
        try {
            retorno = ContatoDao.getInstancia().listar();
        } catch (SQLException sqlException) {
            //TODO Tratar excecao especifica.
        } catch (Exception exception) {
            //TODO Tratar excecao generica.
        }
        return retorno;
    }
    
}
