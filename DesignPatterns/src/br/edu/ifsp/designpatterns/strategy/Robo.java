package br.edu.ifsp.designpatterns.strategy;

/**
 * Entidade robô (padrão Strategy).
 * 
 * @author falvojr
 */
public class Robo {
    private IModo modo;
    
    public Robo(IModo modo) {
        this.modo = modo;
    }

    public IModo getModo() {
        return this.modo;
    }

    public void setModo(IModo modo) {
        this.modo = modo;
    }
    
    public void mover() {
        this.modo.comandoMover();
    }
}
