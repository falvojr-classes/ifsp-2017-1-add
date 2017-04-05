package br.edu.ifsp.controller;

import br.edu.ifsp.dao.ContatoDao;
import br.edu.ifsp.dao.UsuarioDao;
import br.edu.ifsp.model.Usuario;
import br.edu.ifsp.util.ExcecaoNegocial;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public boolean autenticar(Usuario usuario) {
        try {
            if (usuario.getLogin().isEmpty() || usuario.getSenha().isEmpty()) {
                throw new ExcecaoNegocial("Preencha login e senha!");
            } else {
                boolean autenticou = UsuarioDao.getInstancia().autenticar(usuario);
                if (!autenticou) {
                    throw new ExcecaoNegocial("Credenciais inválidas!");
                }
                return autenticou;
            }
        } catch (SQLException sqlException) {
            throw new ExcecaoNegocial("Erro no banco!", sqlException);
        } catch (Exception exception) {
            throw new ExcecaoNegocial("Erro inesperado!", exception);
        }
    }
}
