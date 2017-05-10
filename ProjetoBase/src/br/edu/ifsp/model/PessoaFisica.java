package br.edu.ifsp.model;

import org.joda.time.DateTime;

/**
 * Classe especifica para pessoas fisicas.
 * 
 * @author falvojr
 */
public class PessoaFisica extends Pessoa {
    private String cpf;
    private DateTime dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public DateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
}
