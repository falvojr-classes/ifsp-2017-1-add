
package br.edu.ifsp.designpatterns.singleton;

/**
 *
 * @author falvojr
 */
public class Singleton {
    
    private static Singleton instancia = null;
    
    private Singleton() {
        super();
    }
    
    public static Singleton getInstancia() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }
    
    public void imprimir() {
        System.out.println(this.toString());
    }
}
