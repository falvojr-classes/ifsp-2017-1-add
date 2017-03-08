package br.edu.ifsp.designpatterns;

import br.edu.ifsp.designpatterns.singleton.Singleton;
import br.edu.ifsp.designpatterns.strategy.IModo;
import br.edu.ifsp.designpatterns.strategy.ModoDefensivo;
import br.edu.ifsp.designpatterns.strategy.ModoNormal;
import br.edu.ifsp.designpatterns.strategy.ModoOfensivo;
import br.edu.ifsp.designpatterns.strategy.Robo;
import br.edu.ifsp.designpatterns.templatedmethod.PacoteA;
import br.edu.ifsp.designpatterns.templatedmethod.PacoteB;
import br.edu.ifsp.designpatterns.templatedmethod.Viagem;

/**
 * Classe de testes.
 * 
 * @author falvojr
 */
public class Main {
    
    public static void main(String[] args) {
        Main.testarSingleton();
        Main.testarTemplatedMethod();
        Main.testarStrategy();
    }

    private static void testarStrategy() {
        IModo modoNormal = new ModoNormal();
        IModo modoDefensivo = new ModoDefensivo();
        IModo modoOfensivo = new ModoOfensivo();
        
        Robo r2d2 = new Robo(modoNormal);
        r2d2.mover();
        r2d2.mover();
        r2d2.setModo(modoOfensivo);
        r2d2.mover();
        r2d2.setModo(modoDefensivo);
        r2d2.mover();
    }
    
    private static void testarTemplatedMethod() {
        Viagem viagemA = new PacoteA();
        Viagem viagemB = new PacoteB();
        viagemA.viajar();
        viagemB.viajar();
    }

    private static void testarSingleton() {
        Singleton s1 = Singleton.getInstancia();
        Singleton s2 = Singleton.getInstancia();
        Singleton s3 = Singleton.getInstancia();
        Singleton s4 = Singleton.getInstancia();
        Singleton s5 = Singleton.getInstancia();
        s1.imprimir();
        s2.imprimir();
        s3.imprimir();
        s4.imprimir();
        s5.imprimir();
    }
}
