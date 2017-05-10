package br.edu.ifsp.controller;

import br.edu.ifsp.dao.PessoaDao;
import br.edu.ifsp.model.Pessoa;
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
public final class PessoaController implements ICrud<Pessoa> {

    /* INICIO Singleton "Preguicoso" */
    private static PessoaController instancia;

    public static PessoaController getInstancia() {
        if (instancia == null) {
            instancia = new PessoaController();
        }
        return instancia;
    }

    private PessoaController() {
        super();
    }

    /* FIM Singleton "Preguicoso" */
    @Override
    public void inserir(Pessoa entidade) throws ExcecaoNegocial {
        salvar(entidade);
    }

    @Override
    public void alterar(Pessoa entidade) throws ExcecaoNegocial {
        salvar(entidade);
    }

    private void salvar(Pessoa entidade) throws ExcecaoNegocial {
        try {
            boolean emailValido = !Validador.ehVazio(entidade.getEmail());
            boolean nomeValido = !Validador.ehVazio(entidade.getNome());
            boolean telefoneValido = !Validador.ehVazio(entidade.getTelefone());
            if (emailValido || nomeValido || telefoneValido) {
                if (emailValido) {
                    Validador.validarEmail(entidade.getEmail());
                }
                if (entidade.getId() == null) {
                    PessoaDao.getInstancia().inserir(entidade);
                } else {
                    PessoaDao.getInstancia().alterar(entidade);
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
    public void deletar(Pessoa entidade) throws ExcecaoNegocial {
        try {
            PessoaDao.getInstancia().deletar(entidade);
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, sqlException);
        } catch (Exception exception) {
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, exception);
        }
    }

    @Override
    public List<Pessoa> listar() throws ExcecaoNegocial {
        try {
            return PessoaDao.getInstancia().listar();
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, sqlException);
        } catch (Exception exception) {
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, exception);
        }
    }

}
