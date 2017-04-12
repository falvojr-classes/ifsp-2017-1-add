package br.edu.ifsp.controller;

import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.model.Usuario;
import br.edu.ifsp.util.ExcecaoNegocial;
import br.edu.ifsp.util.Mensagens;
import java.sql.SQLException;

/**
 * Classe responsável pelas regras e tratamentos de Usuario.
 * 
 * @author falvojr
 */
public final class UsuarioController {
    
    /* INICIO Singleton "Preguicoso" */
    
    private static UsuarioController instancia;

    public static UsuarioController getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioController();
        }
        return instancia;
    }
    
    private UsuarioController() {
        super();
    }
    
    /* FIM Singleton "Preguicoso" */
    
    public void autenticar(Usuario usuario) {
        try {
            if (usuario.getLogin().isEmpty() || usuario.getSenha().isEmpty()) {
                throw new ExcecaoNegocial(Mensagens.USUARIO_ERRO_CAMPOS_OBRIGATORIOS);
            } else {
                boolean autenticou = UsuarioDao.getInstancia().autenticar(usuario);
                if (!autenticou) {
                    throw new ExcecaoNegocial(Mensagens.USUARIO_ERRO_LOGIN);
                }
            }
        } catch (ExcecaoNegocial ex) {
            throw  ex;
        } catch (SQLException ex) {
            throw new ExcecaoNegocial(Mensagens.ERRO_BD, ex);
        } catch (Exception ex) {
            throw new ExcecaoNegocial(Mensagens.ERRO_INESPERADO, ex);
        }
    }
}
