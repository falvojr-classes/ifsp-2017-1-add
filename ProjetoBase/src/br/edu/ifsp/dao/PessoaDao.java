package br.edu.ifsp.dao;

import br.edu.ifsp.util.ICrud;
import java.util.List;

import br.edu.ifsp.model.Pessoa;
import br.edu.ifsp.model.PessoaFisica;
import br.edu.ifsp.model.PessoaJuridica;
import br.edu.ifsp.util.Validador;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 * Classe de persistência para a entidades PessoaFisica e PessoaJuridica. Essa
 * classe implementa os padrões Singleton e Strategy.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public class PessoaDao extends BaseDao implements ICrud<Pessoa> {

    /* INICIO Singleton "Apressado" */
    private static final PessoaDao INSTANCIA = new PessoaDao();

    public static PessoaDao getInstancia() {
        return INSTANCIA;
    }

    private PessoaDao() {
        super();
    }

    /* FIM Singleton "Apressado" */
    @Override
    public void inserir(Pessoa pessoa) throws SQLException {
        super.getConexao().setAutoCommit(false);
        String sql = "INSERT INTO pessoa (nome, email, telefone, ativo) VALUES (?, ?, ?, ?);";
        PreparedStatement comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        comando.setString(1, pessoa.getNome());
        comando.setString(2, pessoa.getEmail());
        comando.setString(3, pessoa.getTelefone());
        comando.setBoolean(4, pessoa.isAtivo());
        comando.execute();
        Long idPessoa = super.getIdGerado(comando);
        comando.close();
        if (pessoa instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            sql = "INSERT INTO pessoa_fisica (id_pf, cpf, data_nascimento) VALUES (?, ?, ?);";
            comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            comando.setLong(1, idPessoa);
            comando.setString(2, pf.getCpf());
            DateTime dataNascimento = pf.getDataNascimento();
            comando.setDate(3, dataNascimento == null ? null : new Date(dataNascimento.getMillis()));
        } else {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            sql = "INSERT INTO pessoa_juridica (id_pj, cnpj, ie) VALUES (?, ?, ?);";
            comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            comando.setLong(1, idPessoa);
            comando.setString(2, pj.getCnpj());
            comando.setString(3, pj.getInscricaoEstadual());
        }
        comando.execute();
        pessoa.setId(idPessoa);
        super.getConexao().commit();
    }

    @Override
    public void alterar(Pessoa entidade) throws SQLException {
        //TODO Fazer em casa ;)
    }

    @Override
    public void deletar(Pessoa entidade) throws SQLException {
        //TODO Fazer em casa ;)
    }

    @Override
    public List<Pessoa> listar() throws SQLException {
        List<Pessoa> contatos = new ArrayList<>();
        String sql = "SELECT " +
                "p.id, p.nome, p.email, p.telefone, p.ativo," +
                "pf.cpf, pf.data_nascimento," +
                "pj.cnpj, pj.ie " +
            "FROM pessoa p " +
            "LEFT JOIN pessoa_fisica pf ON pf.id_pf = p.id " +
            "LEFT JOIN pessoa_juridica pj ON pj.id_pj = p.id;";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        ResultSet resultados = comando.executeQuery();
        while (resultados.next()) {
            //TODO Fazer em casa ;)
            //Preencher os dados comuns (Pessoa)
            //Preencher os dados especificos:
            boolean ehPj = Validador.ehVazio(resultados.getString("cpf"));
            if (ehPj) {
                // PessoaJuridica
            } else {
                // PesoaFisica
            }
        }
        resultados.close();
        comando.close();
        return contatos;
    }

}
