package br.edu.ifsp.view;

import br.edu.ifsp.model.Pessoa;
import br.edu.ifsp.model.PessoaFisica;
import br.edu.ifsp.model.PessoaJuridica;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TableModel para pessoas físicas e jurídicas.
 *
 * @author falvojr
 */
public class PessoaTableModel extends AbstractTableModel {

    private static final String[] COLUNAS = new String[]{
        "Nome",         // Indice 0
        "Email",        // Indice 1
        "Telefone",     // Indice 2
        "Documento",    // Indice 3
        "Status"        // Indice 4
    };

    private List<Pessoa> registros;

    public PessoaTableModel(List<Pessoa> registros) {
        this.registros = registros;
    }

    public List<Pessoa> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Pessoa> registros) {
        this.registros = registros;
    }

    @Override
    public int getRowCount() {
        return this.registros.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Pessoa pessoa = this.registros.get(row);
        Object valorCelula;
        switch (column) {
            case 0:
                valorCelula = pessoa.getNome();
                break;
            case 1:
                valorCelula = pessoa.getEmail();
                break;
            case 2:
                valorCelula = pessoa.getTelefone();
                break;
            case 3:
                if (pessoa instanceof PessoaFisica) {
                    valorCelula = ((PessoaFisica) pessoa).getCpf();
                } else {
                    valorCelula = ((PessoaJuridica) pessoa).getCnpj();
                }
                break;
            case 4:
                valorCelula = pessoa.isAtivo() ? "Ativo" : "Inativo";
                break;
            default:
                valorCelula = "";
                break;
        }
        return valorCelula;
    }

    @Override
    public String getColumnName(int column) {
        return COLUNAS[column];
    }

}
