package br.edu.ifsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe base para os DAO's.
 *
 * @author Venilton FalvoJr
 * 
 * @since 14/03/2017
 */
public abstract class BaseDao {

    /**
     * Recupera uma instância única de Connection usando a classe
     * ConnectionManager.
     *
     * @return instância única de Connection.
     */
    protected Connection getConexao() throws SQLException {
        return ConnectionManager.getInstancia().getConexao();
    }

    /**
     * Funcão útil para a recuperação da chave primária gerada a partir de um
     * PreparedStatement.
     *
     * @param statement PreparedStatement criado usando a constante Statement.RETURN_GENERATED_KEYS.
     *
     * @return Long que representa a chave primária gerada.
     * 
     * @throws java.sql.SQLException
     */
    protected Long getIdGerado(PreparedStatement comando) throws SQLException {
        ResultSet chavesGeradas = comando.getGeneratedKeys();
        chavesGeradas.next();
        Long idGerado = chavesGeradas.getLong(1);
        return idGerado;
    }
    
    public void iniciarTransacao() throws SQLException {
        this.getConexao().setAutoCommit(false);
    }
    
    public void finalizarTransacao() throws SQLException {
        this.getConexao().commit();
    }
}
