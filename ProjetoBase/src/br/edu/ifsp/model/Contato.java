package br.edu.ifsp.model;

import java.util.Calendar;

/**
 * Entidade que representa um contato.
 *
 * @author Venilton FalvoJr
 *
 * @since 14/03/2017
 */
public class Contato {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Calendar dataNascimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
}
