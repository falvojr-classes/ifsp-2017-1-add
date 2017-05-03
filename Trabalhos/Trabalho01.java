/**
 * Considerando o método abaixo, execute as seguintes tarefas:
 *     a) Modele um banco de dados considerando as tabelas "pessoa", "pessoa_fisica", "pessoa_juridica";
 * 	   b) Crie as entidades (classes Java) para tais tabelas. Lembre-se de utilizar o conceito de heranca;
 *     c) Inclua o método abaixo em uma classe que implemente o padrão DAO. Adicionalmente, crie um novo
 *        método que liste todas as pessoas (físicas e jurídicas) com seus respectivos CPF/CNPJ;
 *     d) Realize os testes nos dois métodos (via método estático main).
 */
public void inserir(Pessoa pessoa) throws SQLException {
    String sql = "INSERT INTO pessoa (nome) VALUES (?);";
    PreparedStatement comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    comando.setString(1, pessoa.getNome());
    comando.execute();
    Long idPessoa = super.getIdGerado(comando);
    comando.close();
    if(pessoa instanceof PessoaFisica) {
      sql = "INSERT INTO pessoa_fisica (cpf, id_pessoa) VALUES (?, ?);";
      comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      comando.setString(1, pessoa.getCpf());
      comando.setLong(2, idPessoa);
    } else {
      sql = "INSERT INTO pessoa_juridica (cnpj, ie, id_pessoa) VALUES (?, ?, ?);";
      comando = super.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      comando.setString(1, pessoa.getCnpj());
      comando.setString(2, pessoa.getInscricaoEstadual());
      comando.setLong(3, idPessoa);
    }
    comando.execute();
    Long id = super.getIdGerado(comando);
    pessoa.setId(id);
}
