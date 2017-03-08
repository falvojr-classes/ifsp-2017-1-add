package br.edu.ifsp.designpatterns.strategy;

/**
 * Implementacão "Ofensiva" da interface IModo.
 * 
 * @author falvojr
 */
public class ModoOfensivo implements IModo {

    @Override
    public void comandoMover() {
        System.out.println("Movimentando ofensivamente!");
    }
    
}
