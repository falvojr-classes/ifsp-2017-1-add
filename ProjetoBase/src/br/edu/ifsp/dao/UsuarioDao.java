package br.edu.ifsp.dao;

import br.edu.ifsp.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de persistência para a entidade Usuario.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public class UsuarioDao extends BaseDao {
    
    /* INICIO Singleton "Apressado" */
    
    private static final UsuarioDao instancia = new UsuarioDao();

    public static UsuarioDao getInstancia() {
        return instancia;
    }

    private UsuarioDao() {
        super();
    }
    
    /* FIM Singleton "Apressado" */

    public boolean autenticar(Usuario usuario) throws SQLException {
        boolean retorno = false;
        String sql = "SELECT id FROM usuarios WHERE login = ? AND senha = ? AND ativo = true";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setString(1, usuario.getLogin());
        comando.setString(2, usuario.getSenha());
        ResultSet resultados = comando.executeQuery();
        if (resultados.next()) {
            retorno = true;
            usuario.setId(resultados.getLong("id"));
        }
        comando.close();
        return retorno;
    }

}
