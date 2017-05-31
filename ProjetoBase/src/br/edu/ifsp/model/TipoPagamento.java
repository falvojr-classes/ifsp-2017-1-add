package br.edu.ifsp.model;

/**
 * Exemplo de um enum (tipo pagamento).
 * 
 * @author falvojr
 */
public enum TipoPagamento {
    DEBITO(1),
    CREDITO(2),
    DINHEIRO(3),
    CHEQUE(4);

    private final int id;
    
    private TipoPagamento(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public static TipoPagamento get(int id) {
        TipoPagamento[] valoresEnum = values();
        for (TipoPagamento tipoPagamento : valoresEnum) {
            if (tipoPagamento.getId() == id) {
                return tipoPagamento;
            }
        }
        return null;
    }
}
