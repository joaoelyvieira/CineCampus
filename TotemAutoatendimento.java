import java.util.Scanner;

/**
 * Classe responsável pela interação com o cliente: exibe o menu, mostra o
 * mapa e os indicadores de venda a cada ação, encaminha a compra de
 * ingressos e cuida do encerramento do atendimento (por escolha do
 * cliente ou por lotação da sala).
 */
public class TotemAutoatendimento {

    private final Sala sala;
    private final Bilheteria bilheteria;
    private final Scanner sc;

    public TotemAutoatendimento() {
        this.sala = new Sala();
        this.bilheteria = new Bilheteria();
        this.sc = new Scanner(System.in);
    }

    /**
     * Laço principal do totem. Continua atendendo (voltando ao menu) até o
     * cliente escolher encerrar ou até a sala lotar.
     */
    public void iniciar() {
        System.out.println();
        System.out.println("===== CineCampus - Totem de Autoatendimento =====");

        boolean clienteEncerrou = false;

        while (!sala.salaLotada() && !clienteEncerrou) {
            exibirStatusMapa();
            int opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    sala.exibirMapa();
                    break;
                case 2:
                    comprarIngresso();
                    break;
                case 3:
                    bilheteria.exibirResumo();
                    break;
                case 0:
                    clienteEncerrou = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        if (!clienteEncerrou) {
            System.out.println("\nA sala lotou! Encerrando o atendimento automaticamente...");
        }
        encerrar();
    }

    /** Mostra os indicadores (ingressos, bilheteria, ocupação) e o mapa atual da sala. */
    private void exibirStatusMapa() {
        System.out.printf(
            "%nIngressos vendidos: %d   Bilheteria: R$ %.2f   Ocupação: %.1f%%%n",
            bilheteria.getIngressosVendidos(),
            bilheteria.getTotalArrecadado(),
            sala.calcularOcupacao()
        );
        sala.exibirMapa();
    }

    private int lerOpcaoMenu() {
        System.out.println("\nMenu: [1] Ver mapa  [2] Comprar  [3] Resumo  [0] Encerrar");
        return lerInteiro("Opção: ");
    }

    /**
     * Lê um número inteiro digitado pelo cliente, repetindo a pergunta
     * enquanto a entrada não for um número válido (evita que o programa
     * trave ou quebre se o cliente digitar uma letra, por exemplo).
     */
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
            }
        }
    }

    /** Conduz toda a compra de um ingresso: posição, validações, tipo e confirmação. */
    private void comprarIngresso() {
        int fila = lerInteiro("Fila (0-9): ");
        int coluna = lerInteiro("Poltrona (0-9): ");

        if (!sala.validarPosicao(fila, coluna)) {
            System.out.println("Posição fora dos limites da sala!");
            return;
        }

        if (!sala.isDisponivel(fila, coluna)) {
            System.out.println("Essa poltrona já foi vendida! Escolha outra.");
            return;
        }

        TipoIngresso tipo = lerTipoIngresso();
        Ingresso ingresso = new Ingresso(fila, coluna, tipo);
        String categoria = TabelaPreco.getCategoriaFila(fila);

        System.out.printf(
            "Poltrona %s. Valor: R$ %.2f. Confirma a compra? (S/N): ",
            categoria, ingresso.getValor()
        );
        String confirmacao = sc.nextLine().trim();

        if (confirmacao.equalsIgnoreCase("S")) {
            sala.ocuparPoltrona(fila, coluna);
            bilheteria.registrarVenda(ingresso);
            System.out.println("Compra realizada! Bom filme :)");
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    /** Pergunta o tipo de ingresso, repetindo até receber 1 (inteira) ou 2 (meia). */
    private TipoIngresso lerTipoIngresso() {
        while (true) {
            int escolha = lerInteiro("Tipo (1-Inteira / 2-Meia): ");
            if (escolha == 1) {
                return TipoIngresso.INTEIRA;
            } else if (escolha == 2) {
                return TipoIngresso.MEIA;
            }
            System.out.println("Opção inválida! Digite 1 para Inteira ou 2 para Meia.");
        }
    }

    /** Mostra a tela final: mapa, totais da bilheteria, ocupação e classificação da sessão. */
    private void encerrar() {
        System.out.println("\n===== ENCERRAMENTO DO ATENDIMENTO =====");
        sala.exibirMapa();

        double ocupacaoFinal = sala.calcularOcupacao();
        bilheteria.exibirResumo();
        System.out.printf("Ocupação final da sala: %.1f%%%n", ocupacaoFinal);

        bilheteria.classificarSessao(ocupacaoFinal);
        sc.close();
    }
}
