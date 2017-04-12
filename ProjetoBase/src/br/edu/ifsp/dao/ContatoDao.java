package br.edu.ifsp.dao;

import br.edu.ifsp.util.ICrud;
import java.util.List;

import br.edu.ifsp.model.Contato;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe de persistência para a entidade Contato. Essa classe implementa os
 * padrões Singleton e Strategy.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public class ContatoDao extends BaseDao implements ICrud<Contato>{
    
    /* INICIO Singleton "Apressado" */
    
    private static final ContatoDao INSTANCIA = new ContatoDao();

    public static ContatoDao getInstancia() {
        return INSTANCIA;
    }

    private ContatoDao() {
        super();
    }
    
    /* FIM Singleton "Apressado" */
    
    @Override
    public void inserir(Contato entidade) throws SQLException {
        String sql = "INSERT INTO contatos "
                + "(nome, email, telefone, data_nascimento)  "
                + "VALUES  "
                + "(?, ?, ?, ?);";
        PreparedStatement comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        comando.setString(1, entidade.getNome());
        comando.setString(2, entidade.getEmail());
        comando.setString(3, entidade.getTelefone());
        Date data = new Date(entidade.getDataNascimento().getTimeInMillis());
        comando.setDate(4, data);
        comando.execute();
        
        Long idGerado = super.getIdGerado(comando);
        entidade.setId(idGerado);
        
        comando.close();
    }

    @Override
    public void alterar(Contato entidade) throws SQLException {
        String sql = "UPDATE contatos SET" +
                        " nome = ?," +
                        " email = ?," +
                        " telefone = ?," +
                        " data_nascimento = ?" +
                        " WHERE id = ?";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setString(1, entidade.getNome());
        comando.setString(2, entidade.getEmail());
        comando.setString(3, entidade.getTelefone());
        Date data = new Date(entidade.getDataNascimento().getTimeInMillis());
        comando.setDate(4, data);
        comando.setLong(5, entidade.getId());
        comando.execute();
        comando.close();
    }

    @Override
    public void deletar(Contato entidade) throws SQLException {
        String sql = "DELETE FROM contatos WHERE id = ?";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setLong(1, entidade.getId());
        comando.execute();
        comando.close();
    }

    @Override
    public List<Contato> listar() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        ResultSet resultados = comando.executeQuery();
        while (resultados.next()) {            
            Contato contato = new Contato();
            contato.setNome(resultados.getString("nome"));
            contato.setEmail(resultados.getString("email"));
            contato.setTelefone(resultados.getString("telefone"));
            Date dataSql = resultados.getDate("data_nascimento");
            Calendar dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(dataSql);
            contato.setDataNascimento(dataNascimento);
            
            contatos.add(contato);
        }
        resultados.close();
        comando.close();
        return contatos;
    }

}
