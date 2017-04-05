package br.edu.ifsp.util;

/**
 * Excecao generica de negocio.
 * 
 * @author falvojr
 */
public class ExcecaoNegocial extends RuntimeException {

    public ExcecaoNegocial(String mensagem) {
        super(mensagem);
    }
    
    public ExcecaoNegocial(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
