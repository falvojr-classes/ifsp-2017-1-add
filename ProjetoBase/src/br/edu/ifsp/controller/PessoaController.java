package br.edu.ifsp.controller;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifsp.dao.PessoaDao;
import br.edu.ifsp.model.Pessoa;
import br.edu.ifsp.model.PessoaFisica;
import br.edu.ifsp.model.PessoaJuridica;
import br.edu.ifsp.util.ExcecaoNegocial;
import br.edu.ifsp.util.ICrud;
import br.edu.ifsp.util.Mensagens;
import br.edu.ifsp.util.Validador;
import java.sql.SQLException;
import java.util.List;
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
            boolean emailVazio = Validador.ehVazio(entidade.getEmail());
            boolean nomeVazio = Validador.ehVazio(entidade.getNome());
            boolean telefoneVazio = Validador.ehVazio(entidade.getTelefone());
            if (emailVazio || nomeVazio || telefoneVazio) {
                throw new ExcecaoNegocial(Mensagens.PESSOA_ERRO_CAMPOS_OBRIGATORIOS);
            } else {
                if (!emailVazio) {
                    Validador.validarEmail(entidade.getEmail());
                }
                // Validacao de documentos usando Caelum Stella (Core)
                // https://github.com/caelum/caelum-stella/wiki
                if (entidade instanceof PessoaFisica) {
                    PessoaFisica pf = (PessoaFisica) entidade;
                    if (Validador.ehVazio(pf.getCpf())) {
                        throw new ExcecaoNegocial(Mensagens.PESSOA_ERRO_CAMPOS_OBRIGATORIOS);
                    } else {
                        CPFValidator validator = new CPFValidator(true);
                        validator.assertValid(pf.getCpf());
                    }
                } else {
                    PessoaJuridica pj = (PessoaJuridica) entidade;
                    if (Validador.ehVazio(pj.getCnpj())) {
                        throw new ExcecaoNegocial(Mensagens.PESSOA_ERRO_CAMPOS_OBRIGATORIOS);
                    } else {
                        CNPJValidator validator = new CNPJValidator(true);
                        validator.assertValid(pj.getCnpj());
                    }
                }
                if (entidade.getId() == null) {
                    PessoaDao.getInstancia().inserir(entidade);
                } else {
                    PessoaDao.getInstancia().alterar(entidade);
                }
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
        } catch (InvalidStateException ex) {
            // Exception lançada quando o documento é inválido
            throw new ExcecaoNegocial(Mensagens.ERRO_CPF_CNPJ, ex);
        } catch (Exception ex) {
            // Manipula uma excecão genérica.
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, ex);
        }
    }

    @Override
    public void deletar(Long id) throws ExcecaoNegocial {
        try {
            PessoaDao.getInstancia().deletar(id);
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
