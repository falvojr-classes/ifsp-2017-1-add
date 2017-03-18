package br.edu.ifsp.dao;

import java.sql.Connection;

/**
 * Classe base para os DAO's.
 *
 * @author Venilton FalvoJr
 * 
 * @since 14/03/2017
 */
public class BaseDao {

    /**
     * Método que recupera uma instância única de Connection usando a classe
     * ConnectionManager.
     *
     * @return instância única de Connection.
     */
    protected Connection getConexao() {
        return ConnectionManager.getInstancia().getConexao();
    }

}
