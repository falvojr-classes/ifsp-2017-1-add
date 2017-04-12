package br.edu.ifsp.controller;

import br.edu.ifsp.dao.ContatoDao;
import br.edu.ifsp.model.Contato;
import br.edu.ifsp.util.ExcecaoNegocial;
import java.util.List;
import br.edu.ifsp.util.ICrud;
import br.edu.ifsp.util.Mensagens;
import java.sql.SQLException;

/**
 * Classe responsável pelas regras e tratamentos de Contato.
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
            boolean emailValido = !entidade.getEmail().isEmpty();
            boolean nomeValido = !entidade.getNome().isEmpty();
            boolean telefoneValido = !entidade.getTelefone().isEmpty();
            if (emailValido || nomeValido || telefoneValido) {
                ContatoDao.getInstancia().inserir(entidade);
            } else {
                throw new ExcecaoNegocial(Mensagens.CONTATO_ERRO_CAMPOS_OBRIGATORIOS);
            }
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
