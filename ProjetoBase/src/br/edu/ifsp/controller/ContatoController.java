package br.edu.ifsp.controller;

import br.edu.ifsp.dao.ContatoDao;
import br.edu.ifsp.model.Contato;
import br.edu.ifsp.util.ExcecaoNegocial;
import java.util.List;
import br.edu.ifsp.util.ICrud;
import br.edu.ifsp.util.Mensagens;
import br.edu.ifsp.util.Validador;
import java.sql.SQLException;
import javax.mail.internet.AddressException;

/**
 * Classe responsável pelas regras e tratamentos de Contato.
 *
 * @author falvojr
 */
public final class ContatoController implements ICrud<Contato> {

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
    public void inserir(Contato entidade) throws ExcecaoNegocial {
        salvar(entidade);
    }

    @Override
    public void alterar(Contato entidade) throws ExcecaoNegocial {
        salvar(entidade);
    }

    private void salvar(Contato entidade) throws ExcecaoNegocial {
        try {
            boolean emailValido = !entidade.getEmail().isEmpty();
            boolean nomeValido = !entidade.getNome().isEmpty();
            boolean telefoneValido = !entidade.getTelefone().isEmpty();
            if (emailValido || nomeValido || telefoneValido) {
                if (emailValido) {
                    Validador.validarEmail(entidade.getEmail());
                }
                if (entidade.getId() == null) {
                    ContatoDao.getInstancia().inserir(entidade);
                } else {
                    ContatoDao.getInstancia().alterar(entidade);
                }
            } else {
                throw new ExcecaoNegocial(Mensagens.CONTATO_ERRO_CAMPOS_OBRIGATORIOS);
            }
        } catch (ExcecaoNegocial ex) {
            // Excecão já foi instanciada corretamente, então somente a propagamos.
            throw ex;
        } catch (SQLException ex) {
            // Manipula (instancia) uma excecão no banco de dados (erro específico).
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, ex);
        } catch (AddressException ex) {
            // Manipula uma excecão na validacão de email.
            throw new ExcecaoNegocial(Mensagens.ERRO_EMAIL, ex);
        } catch (Exception ex) {
            // Manipula uma excecão genérica.
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, ex);
        }
    }

    @Override
    public void deletar(Contato entidade) throws ExcecaoNegocial {
        try {
            ContatoDao.getInstancia().deletar(entidade);
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, sqlException);
        } catch (Exception exception) {
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, exception);
        }
    }

    @Override
    public List<Contato> listar() throws ExcecaoNegocial {
        try {
            return ContatoDao.getInstancia().listar();
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, sqlException);
        } catch (Exception exception) {
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, exception);
        }
    }

}
