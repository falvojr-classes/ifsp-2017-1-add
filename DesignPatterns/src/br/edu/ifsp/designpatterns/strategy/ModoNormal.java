package br.edu.ifsp.designpatterns.strategy;

/**
 * Implementacão "Normal" da interface IModo.
 * 
 * @author falvojr
 */
public class ModoNormal implements IModo {

    @Override
    public void comandoMover() {
        System.out.println("Movimentando normalmente!");
    }
    
}
