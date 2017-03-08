package br.edu.ifsp.designpatterns.templatedmethod;

/**
 * Classe abstrata (padrão Templated Method).
 * 
 * @author falvojr
 */
public abstract class Viagem {
 
    protected abstract void realizarIda();
    protected abstract void realizarDiaA();
    protected abstract void realizarDiaB();
    protected abstract void realizarDiaC();
    protected abstract void realizarVolta();
    
    public final void viajar() {
        this.realizarIda();
        this.realizarDiaA();
        this.realizarDiaB();
        this.realizarDiaC();
        this.realizarVolta();
    }
}
