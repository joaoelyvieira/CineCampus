/**
 * Representa uma poltrona da sala de cinema.
 * Guarda sua posição (fila e coluna) e seu estado (livre ou ocupada).
 */
public class Poltrona {

    private final int fila;
    private final int coluna;
    private boolean ocupada;

    public Poltrona(int fila, int coluna) {
        this.fila = fila;
        this.coluna = coluna;
        this.ocupada = false; // toda poltrona nasce livre
    }

    public int getFila() {
        return fila;
    }

    public int getColuna() {
        return coluna;
    }

    /** Indica se a poltrona ainda não foi vendida. */
    public boolean isLivre() {
        return !this.ocupada;
    }

    /** Marca a poltrona como vendida. */
    public void ocupar() {
        this.ocupada = true;
    }

    /** Libera a poltrona (útil em caso de cancelamento da venda). */
    public void liberar() {
        this.ocupada = false;
    }

    /**
     * Retorna o símbolo usado no mapa da sala:
     * "-" para poltrona livre e "#" para poltrona já vendida.
     */
    public String getSimbolo() {
        return this.ocupada ? "#" : "-";
    }
}
