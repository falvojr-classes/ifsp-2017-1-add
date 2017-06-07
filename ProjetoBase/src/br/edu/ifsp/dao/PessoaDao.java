package br.edu.ifsp.dao;

import br.edu.ifsp.util.ICrud;
import java.util.List;

import br.edu.ifsp.model.Pessoa;
import br.edu.ifsp.model.PessoaFisica;
import br.edu.ifsp.model.PessoaJuridica;
import br.edu.ifsp.util.Validador;
import java.sql.Connection;
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
        final Connection conexao = super.getConexao();
        conexao.setAutoCommit(false);
        String sql = "INSERT INTO pessoa (nome, email, telefone, ativo) VALUES (?, ?, ?, ?);";
        PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        comando.setString(1, pessoa.getNome());
        comando.setString(2, pessoa.getEmail());
        comando.setString(3, pessoa.getTelefone());
        comando.setBoolean(4, pessoa.isAtivo());
        comando.execute();
        Long idPessoa = super.getIdGerado(comando);
        comando.close();
        if (pessoa instanceof PessoaFisica) {
            inserirPf(pessoa, idPessoa);
        } else {
            inserirPj(pessoa, idPessoa);
        }
        comando.execute();
        pessoa.setId(idPessoa);
        conexao.commit();
    }

    private void inserirPj(Pessoa pessoa, Long idPessoa) throws SQLException {
        PessoaJuridica pj = (PessoaJuridica) pessoa;
        String sql = "INSERT INTO pessoa_juridica (id_pj, cnpj, ie) VALUES (?, ?, ?);";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setLong(1, idPessoa);
        comando.setString(2, pj.getCnpj());
        comando.setString(3, pj.getInscricaoEstadual());
        comando.execute();
        comando.close();
    }

    private void inserirPf(Pessoa pessoa, Long idPessoa) throws SQLException {
        PessoaFisica pf = (PessoaFisica) pessoa;
        String sql = "INSERT INTO pessoa_fisica (id_pf, cpf, data_nascimento) VALUES (?, ?, ?);";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setLong(1, idPessoa);
        comando.setString(2, pf.getCpf());
        DateTime dateJoda = pf.getDataNascimento();
        comando.setDate(3, dateJoda == null ? null : new Date(dateJoda.getMillis()));
        comando.execute();
        comando.close();
    }

    @Override
    public void alterar(Pessoa pessoa) throws SQLException {
        final Connection conexao = super.getConexao();
        conexao.setAutoCommit(false);
        String sql = "UPDATE pessoa SET nome = ?, email = ?, telefone = ?, ativo = ? WHERE id = ?";
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, pessoa.getNome());
        comando.setString(2, pessoa.getEmail());
        comando.setString(3, pessoa.getTelefone());
        comando.setBoolean(4, pessoa.isAtivo());
        comando.setLong(5, pessoa.getId());
        comando.execute();
        comando.close();
        if (pessoa instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            sql = "UPDATE pessoa_fisica SET cpf = ?, data_nascimento = ? WHERE id_pf = ?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1, pf.getCpf());
            DateTime dateJoda = pf.getDataNascimento();
            comando.setDate(2, dateJoda != null ? new Date(dateJoda.getMillis()) : null);
            comando.setLong(3, pf.getId());
            int linhasAfetadas = comando.executeUpdate();
            //TODO Tratar troca de tipo (fisica -> jurídica)
            if (linhasAfetadas == 0) {
                //TODO Excluir i registro em pessoa_juridica
                
                //FEITO Inserir pessoa_fisica
                this.inserirPf(pessoa, pessoa.getId());
            }
        } else {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            sql = "UPDATE pessoa_juridica SET cnpj = ?, ie = ? WHERE id_pj = ?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1, pj.getCnpj());
            comando.setString(2, pj.getInscricaoEstadual());
            comando.setLong(3, pj.getId());    
            int linhasAfetadas = comando.executeUpdate();
            //TODO Tratar troca de tipo (jurica -> fisica)
            if (linhasAfetadas == 0) {
                //TODO Excluir i registro em pessoa_fisica
                
                //FEITO Inserir pessoa_juridica
                this.inserirPj(pessoa, pessoa.getId());
            }
        }
        conexao.commit();
    }

    @Override
    public void deletar(Long id) throws SQLException {
        String sql = "UPDATE pessoa SET ativo = false WHERE id = ?";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        comando.setLong(1, id);
        comando.execute();
        comando.close();
    }

    @Override
    public List<Pessoa> listar() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT " +
                "p.id, p.nome, p.email, p.telefone, p.ativo," +
                "pf.cpf, pf.data_nascimento," +
                "pj.cnpj, pj.ie " +
            "FROM pessoa p " +
            "LEFT JOIN pessoa_fisica pf ON pf.id_pf = p.id " +
            "LEFT JOIN pessoa_juridica pj ON pj.id_pj = p.id;";
        PreparedStatement comando = super.getConexao().prepareStatement(sql);
        ResultSet rs = comando.executeQuery();
        while (rs.next()) {
            //FEITO Fazer em casa ;) 
            Pessoa pessoa;
            //Preencher os dados especificos:
            boolean ehPj = Validador.ehVazio(rs.getString("cpf"));
            if (ehPj) {
                PessoaJuridica pj = new PessoaJuridica();
                pj.setCnpj(rs.getString("cnpj"));
                pj.setInscricaoEstadual(rs.getString("ie"));
                pessoa = pj;
            } else {
                PessoaFisica pf = new PessoaFisica();
                pf.setCpf(rs.getString("cpf"));
                Date dateSql = rs.getDate("data_nascimento");
                pf.setDataNascimento(new DateTime(dateSql));
                pessoa = pf;
            }
            //Preencher os dados comuns (Pessoa):
            pessoa.setId(rs.getLong("id"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setAtivo(rs.getBoolean("ativo"));
            pessoas.add(pessoa);
        }
        rs.close();
        comando.close();
        return pessoas;
    }

}
