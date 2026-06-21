
import java.util.Random;

/**
 * Representa a sala de exibição: uma matriz 10x10 de poltronas. É responsável
 * por controlar a ocupação, validar posições escolhidas pelo cliente e desenhar
 * o mapa da sala no terminal.
 */
public class Sala {

    public static int tamanho = 10; // 10 filas x 10 poltronas por fila
    private static int poltronasPreVendidas = 15; // vendas feitas pela internet

    private final Poltrona[][] poltronas; // matriz 10x10 que representa o mapa da sala

    public Sala() {
        this.poltronas = new Poltrona[tamanho][tamanho];
        for (int fila = 0; fila < tamanho; fila++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                this.poltronas[fila][coluna] = new Poltrona(fila, coluna);
            }
        }
        venderPoltronasIniciais(poltronasPreVendidas);
    }

    /**
     * Simula as vendas feitas pela internet antes da abertura do totem,
     * ocupando "quantidade" poltronas em posições aleatórias e sem repetição,
     * usando a classe Random.
     */
    private void venderPoltronasIniciais(int quantidade) {
        Random sorteio = new Random();
        int vendidas = 0;
        while (vendidas < quantidade) {
            int fila = sorteio.nextInt(tamanho);
            int coluna = sorteio.nextInt(tamanho);
            if (this.poltronas[fila][coluna].isLivre()) {
                this.poltronas[fila][coluna].ocupar();
                vendidas++;
            }
        }
    }

    /**
     * Verifica se a fila e a coluna informadas existem dentro da sala.
     */
    public boolean validarPosicao(int fila, int coluna) {
        return fila >= 0 && fila < tamanho && coluna >= 0 && coluna < tamanho;
    }

    /**
     * Verifica se a poltrona existe e está livre para venda.
     */
    public boolean isDisponivel(int fila, int coluna) {
        return validarPosicao(fila, coluna) && this.poltronas[fila][coluna].isLivre();
    }

    /**
     * Marca a poltrona indicada como vendida.
     */
    public void ocuparPoltrona(int fila, int coluna) {
        if (validarPosicao(fila, coluna)) {
            this.poltronas[fila][coluna].ocupar();
        }
    }

    /**
     * A sala está lotada quando a ocupação atinge 100%.
     */
    public boolean salaLotada() {
        return calcularOcupacao() >= 100.0;
    }

    /**
     * Calcula a taxa de ocupação atual da sala, em percentual (0 a 100).
     */
    public double calcularOcupacao() {
        int ocupadas = 0;
        for (int fila = 0; fila < tamanho; fila++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                if (!this.poltronas[fila][coluna].isLivre()) {
                    ocupadas++;
                }
            }
        }
        return (ocupadas * 100.0) / (tamanho * tamanho);
    }

    /**
     * Desenha o mapa da sala no terminal, mostrando os números das filas e das
     * poltronas, além do estado de cada uma ("-" livre, "#" ocupada).
     */
    public void exibirMapa() {
        System.out.println();

        // Cabeçalho com o número de cada poltrona (coluna)
        System.out.print("     ");
        for (int coluna = 0; coluna < tamanho; coluna++) {
            System.out.printf("%2d ", coluna);
        }
        System.out.println();

        // Uma linha por fila, com o número da fila à esquerda
        for (int fila = 0; fila < tamanho; fila++) {
            System.out.printf(" %2d  ", fila);
            for (int coluna = 0; coluna < tamanho; coluna++) {
                System.out.printf("%2s ", this.poltronas[fila][coluna].getSimbolo());
            }
            System.out.println();
        }
    }
}
