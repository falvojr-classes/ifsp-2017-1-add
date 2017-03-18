package br.edu.ifsp;

import br.edu.ifsp.dao.ConnectionManager;

/**
 * Classe principal para testes.
 * 
 * @author falvojr
 */
public class Main {

    public static void main(String[] args) {
        ConnectionManager.getInstancia().getConexao();
    }
    
}
