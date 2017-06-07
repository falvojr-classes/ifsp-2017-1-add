package br.edu.ifsp.util;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface generica para as operacoes padrão (CRUD).
 * 
 * @author falvojr
 * @param <T> parametro generico de tipo.
 */
public interface ICrud<T> {
    void inserir(T entidade) throws SQLException;
    void alterar(T entidade) throws SQLException;
    void deletar(Long id) throws SQLException;
    List<T> listar() throws SQLException;
}
