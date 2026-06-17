
/**
 * Guarda e calcula os preços dos ingressos do CineCampus.
 * O preço base depende da fila escolhida (poltronas mais perto da tela
 * são mais baratas, as do fundo são VIP) e é reduzido à metade quando
 * o ingresso é meia-entrada.
 */
public class TabelaPreco {

    // Vetor com o preço base de cada uma das 10 filas da sala (uma posição por fila).
    private static final double[] PRECO_POR_FILA = new double[Sala.tamanho];

    // Bloco estático: monta o vetor de preços uma única vez, seguindo a regra do enunciado.
    static {
        for (int fila = 0; fila < Sala.tamanho; fila++) {
            if (fila <= 1) {
                PRECO_POR_FILA[fila] = 15.00; // filas 0 e 1 - frente
            } else if (fila <= 7) {
                PRECO_POR_FILA[fila] = 25.00; // filas 2 a 7 - meio
            } else {
                PRECO_POR_FILA[fila] = 35.00; // filas 8 e 9 - VIP
            }
        }
    }

    /**
     * Retorna o preço base (inteira) da fila informada.
     */
    public static double getPrecoFila(int fila) {
        return PRECO_POR_FILA[fila];
    }

    /**
     * Retorna o preço final do ingresso, já considerando o tipo (inteira ou
     * meia).
     */
    public static double getPreco(int fila, TipoIngresso tipo) {
        double preco = getPrecoFila(fila);
        if (tipo == TipoIngresso.MEIA) {
            preco = preco / 2.0; // meia-entrada paga metade do preço da fila
        }
        return preco;
    }

    /**
     * Retorna o nome da categoria da fila (Frente, Meio ou VIP), só para
     * exibição.
     */
    public static String getCategoriaFila(int fila) {
        if (fila <= 1) {
            return "Frente";
        } else if (fila <= 7) {
            return "Meio";
        } else {
            return "VIP";
        }
    }
}
