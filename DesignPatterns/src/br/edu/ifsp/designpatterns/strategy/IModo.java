package br.edu.ifsp.designpatterns.strategy;

/**
 * Interface para abstracão dos modos do Robo.
 * Nota: Interfaces podem herdar outras interfaces. Nesse caso, deve-se utilizar
 * o comando "extends" ao invés de "implements".
 * 
 * @author falvojr
 */
public interface IModo {   
    public void comandoMover();
}
