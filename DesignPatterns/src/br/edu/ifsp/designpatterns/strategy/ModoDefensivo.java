package br.edu.ifsp.designpatterns.strategy;

/**
 * Implementacão "Defensiva" da interface IModo.
 * 
 * @author falvojr
 */
public class ModoDefensivo implements IModo {

    @Override
    public void comandoMover() {
        System.out.println("Movimentando defensivamente!");
    }
    
}
