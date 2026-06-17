/**
 * Controla a bilheteria do totem: total arrecadado, quantidade de
 * ingressos vendidos e a classificação final da sessão.
 */
public class Bilheteria {

    private int ingressosVendidos;
    private double totalArrecadado;

    /** Registra a venda de um ingresso, atualizando o total e a contagem. */
    public void registrarVenda(Ingresso ingresso) {
        this.ingressosVendidos++;
        this.totalArrecadado += ingresso.getValor();
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    /** Mostra o resumo de vendas realizadas pelo totem. */
    public void exibirResumo() {
        System.out.println("\n--- Resumo de vendas ---");
        System.out.printf("Ingressos vendidos pelo totem: %d%n", this.ingressosVendidos);
        System.out.printf("Bilheteria acumulada: R$ %.2f%n", this.totalArrecadado);
    }

    /**
     * Classifica a sessão de acordo com a taxa de ocupação final da sala,
     * seguindo exatamente as faixas definidas no enunciado.
     */
    public void classificarSessao(double ocupacaoFinal) {
        String classificacao;
        if (ocupacaoFinal >= 90) {
            classificacao = "Sessão Esgotada - Sucesso de Bilheteria!";
        } else if (ocupacaoFinal >= 70) {
            classificacao = "Casa Cheia";
        } else if (ocupacaoFinal >= 40) {
            classificacao = "Sessão Mediana";
        } else {
            classificacao = "Sala Vazia - precisa divulgar mais";
        }
        System.out.println("Classificação da sessão: " + classificacao);
    }
}
