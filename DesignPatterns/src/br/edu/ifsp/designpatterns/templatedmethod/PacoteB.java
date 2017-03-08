package br.edu.ifsp.designpatterns.templatedmethod;

/**
 * Classe concreta para o pacote de viagem B (padrão Templated Method).
 * 
 * @author falvojr
 */
public class PacoteB extends Viagem {

    @Override
    protected void realizarIda() {
        System.out.println("Vai para Américo Brasiliense!");
    }

    @Override
    protected void realizarDiaA() {
        System.out.println("Visita o Caneco 90!");
    }

    @Override
    protected void realizarDiaB() {
        System.out.println("Visita à Estacão de Trem!");
    }

    @Override
    protected void realizarDiaC() {
        System.out.println("Visita o Ginásio de Esportes!");
    }

    @Override
    protected void realizarVolta() {
        System.out.println("Volta de Américo Brasiliense!");

    }
    
}
