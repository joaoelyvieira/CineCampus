/**
 * Representa um ingresso comprado pelo cliente: guarda a poltrona escolhida
 * (fila e coluna), o tipo (inteira ou meia) e sabe calcular seu próprio valor.
 */
public class Ingresso {

    private final int fila;
    private final int coluna;
    private final TipoIngresso tipo;

    public Ingresso(int fila, int coluna, TipoIngresso tipo) {
        this.fila = fila;
        this.coluna = coluna;
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColuna() {
        return coluna;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    /** Valor do ingresso: depende da fila da poltrona e do tipo (inteira/meia). */
    public double getValor() {
        return TabelaPreco.getPreco(this.fila, this.tipo);
    }
}
