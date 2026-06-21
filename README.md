# CineCampus — Totem de Autoatendimento de Cinema

Trabalho final, obrigatório, da disciplina de **Introdução à Programação** da **FURB (Universidade Regional de Blumenau)**, desenvolvido em dupla por **João Ely Vieira** e **Diogo Rafael Soares**.

O professor propôs ainda um desafio opcional dentro do trabalho: resolver o problema utilizando **Programação Orientada a Objetos (POO)**, com responsabilidades bem definidas entre as classes. Quem completasse esse desafio ganhava um bônus de 0,5 na média da disciplina.

## Sobre o projeto

O CineCampus simula um totem de autoatendimento para venda de ingressos de cinema via terminal. O sistema controla uma sala de exibição (matriz 10x10 de poltronas), permite a escolha de poltronas livres, calcula o valor do ingresso de acordo com a fila e o tipo (inteira ou meia-entrada), e mantém um resumo de vendas durante o atendimento.

## Estrutura de classes

- **Main** — ponto de entrada da aplicação.
- **TotemAutoatendimento** — controla o fluxo de interação com o cliente (menu, exibição do mapa da sala e dos indicadores de venda, encaminhamento da compra).
- **Sala** — representa a sala de exibição (matriz 10x10 de poltronas), valida posições e controla a ocupação.
- **Poltrona** — representa uma poltrona individual, com posição (fila e coluna) e estado (livre/ocupada).
- **Ingresso** — representa um ingresso comprado, vinculado a uma poltrona e a um tipo, e calcula seu próprio valor.
- **TipoIngresso** — enum com os tipos de ingresso disponíveis (inteira e meia).
- **TabelaPreco** — define e calcula os preços por fila (frente, meio e VIP), aplicando o desconto de meia-entrada.
- **Bilheteria** — controla o total arrecadado e a quantidade de ingressos vendidos pelo totem.

## Como executar

```bash
javac *.java
java Main
```

## Divisão do trabalho

O desenvolvimento foi dividido entre os integrantes da dupla, com integração das partes para formar o sistema completo.
