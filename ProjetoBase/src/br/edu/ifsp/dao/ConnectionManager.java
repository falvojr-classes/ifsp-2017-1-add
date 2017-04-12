package br.edu.ifsp.dao;

import br.edu.ifsp.util.ExcecaoNegocial;
import br.edu.ifsp.util.Mensagens;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que gerencia uma conexão única com o banco de dados. Para isso, a
 * classe mantem um atributo do tipo Connection e implementa o padrão de
 * projetos Singleton.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public final class ConnectionManager {

    /* INICIO Singleton "Apressado" */
    
    private static final ConnectionManager INSTANCIA = new ConnectionManager();

    public static ConnectionManager getInstancia() {
        return ConnectionManager.INSTANCIA;
    }

    private ConnectionManager() {
        super();
    }
    
    /* FIM Singleton "Apressado" */

    private Connection conexao;

    public Connection getConexao() throws SQLException {
        if (this.conexao == null) {
            this.conexao = this.newConnection();
        }
        return this.conexao;
    }

    /**
     * Cria uma NOVA conexão pronta para uso. Por isso, limitamos essa chamada
     * ao construtor da classe ConnectionManager, que implementa o padrão de
     * projeto Singleton.
     *
     * @return conexão do tipo Connection.
     */
    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/agenda", "roota", "select*from0");
    }
}
