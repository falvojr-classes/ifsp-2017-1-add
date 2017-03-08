package br.edu.ifsp.designpatterns.templatedmethod;

/**
 * Classe concreta para o pacote de viagem A (padrão Templated Method).
 * 
 * @author falvojr
 */
public class PacoteA extends Viagem {

    @Override
    protected void realizarIda() {
        System.out.println("Vai para Araraquara!");
    }

    @Override
    protected void realizarDiaA() {
        System.out.println("Visita ao IFSP!");
    }

    @Override
    protected void realizarDiaB() {
        System.out.println("Visita à Prefeitura!");
    }

    @Override
    protected void realizarDiaC() {
        System.out.println("Visita ao Shopping Jaraguá!");
    }

    @Override
    protected void realizarVolta() {
        System.out.println("Volta de Araraquara!");

    }
    
}
