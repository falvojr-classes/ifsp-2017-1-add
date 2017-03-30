package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que gerencia uma conexão única com o banco de dados. Para isso, a
 * classe mantem um atributo do tipo Connection e implementa o padrão de projetos
 * Singleton.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public final class ConnectionManager {
    
    private static final ConnectionManager instancia = new ConnectionManager();

    public static ConnectionManager getInstancia() {
        return ConnectionManager.instancia;
    }

    private ConnectionManager() {
        // Atributo setado uma única vez (devido ao Singleton)
        this.conexao = this.newConnection();
    }
    
    private final Connection conexao;

    public Connection getConexao() {
        return this.conexao;
    }

    /**
     * Cria uma NOVA conexão pronta para uso. Por isso, limitamos essa 
     * chamada ao construtor da classe ConnectionManager, que implementa 
     * o padrão de projeto Singleton.
     *
     * @return conexão do tipo Connection.
     */
    private Connection newConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "select*from0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
