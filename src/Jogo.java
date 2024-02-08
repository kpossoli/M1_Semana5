/*[M1S05] EX 6 - Crie o Jogo

Crie a Classe Jogo, essa classe vai ter o jogo em si, o jogo consiste em um pedra papel e tesoura, que o jogador deve inserir a sua jogada e o sistema irá retorna se ele ganhou ou não.
O Jogo deve estar em um método jogar().
O Jogo deve ter o atributo, melhor jogador(melhorJogador) e deve ter o número de vezes que o jogo foi jogado (numeroJogadas).
Cada vez que um jogador ganha ele deve receber mais um ponto, cada tentativa adiciona ao numero de tentativas.
 */

/*[M1S05] EX 7 - Sobrecarga de Jogos

Crie uma sobrecarga do jogar() que será um novo jogo, esse deve receber um número, jogar(int num).
O jogo consiste em o jogador escolher um número de 0 até ‘num’(numero colocado no parâmetro). Se acertar ganha um ponto, se errar ele perde um ponto.
Tanto o número 'num', quanto o valor que o jogador escolher no jogo devem ser coletado por input no console.
 */

import java.util.*;

public class Jogo {
    private Jogador melhorJogador; // Melhor jogador do jogo
    private int numeroJogadas; // Número total de jogadas realizadas

    public Jogo() {
        this.melhorJogador = null; // Inicialmente não há melhor jogador
        this.numeroJogadas = 0; // Inicialmente não houve jogadas
    }

    // Método para jogar Pedra, Papel e Tesoura
    public void jogarPedraPapelTesoura(List<Jogador> listaJogadores) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha Pedra, Papel ou Tesoura:");
            String escolhaJogador = scanner.nextLine().toLowerCase();

            // Verificar se a escolha do jogador é válida
            while (!escolhaJogador.equals("pedra") && !escolhaJogador.equals("papel") && !escolhaJogador.equals("tesoura")) {
                System.out.println("Opção inválida! Por favor, escolha Pedra, Papel ou Tesoura:");
                escolhaJogador = scanner.nextLine().toLowerCase();
            }

            // Gerar jogada do computador
            String jogadaComputador = gerarJogadaComputador();

            // Verificar o resultado do jogo
            String resultado = verificarResultado(escolhaJogador, jogadaComputador);

            // Atualizar a pontuação do jogador
            for (Jogador jogador : listaJogadores) {
                if (resultado.equals("Você ganhou!") && jogador.equals(this.melhorJogador)) {
                    jogador.adicionaPontos(1);
                } else if (jogador.equals(this.melhorJogador)) {
                    jogador.perdePontos(1);
                }
            }

            // Exibir as escolhas e o resultado do jogo
            System.out.println("Você escolheu: " + escolhaJogador);
            System.out.println("O computador escolheu: " + jogadaComputador);
            System.out.println("Resultado: " + resultado);

            // Incrementar o número de jogadas
            this.numeroJogadas++;

            // Perguntar se o jogador deseja jogar novamente
            System.out.println("Deseja jogar novamente? (s/n)");
            String continuar = scanner.nextLine().toLowerCase();
            if (!continuar.equals("s")) {
                break;
            }
        }
    }

    // Método para jogar Adivinhar o Número
    public void jogarAdivinharNumero(List<Jogador> listaJogadores, Scanner scanner, int limiteSuperior) {
        while (true) {
            System.out.println("Digite um número entre 0 e " + limiteSuperior + ":");
            int numeroEscolhido;

            try {
                numeroEscolhido = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha após a leitura do número

                // Verificar se o número está dentro do intervalo permitido
                if (numeroEscolhido < 0 || numeroEscolhido > limiteSuperior) {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Número inválido! Por favor, digite um número válido entre 0 e " + limiteSuperior + ".");
                scanner.nextLine(); // Limpar o buffer do scanner
                continue; // Reiniciar o loop para pedir outra entrada válida
            }

            // Gerar um número aleatório
            int numeroAleatorio = (int) (Math.random() * (limiteSuperior + 1));

            // Verificar se o número escolhido pelo jogador é igual ao número aleatório
            if (numeroEscolhido == numeroAleatorio) {
                System.out.println("Parabéns! Você acertou!");
                this.melhorJogador.adicionaPontos(1);
            } else {
                System.out.println("Você errou! O número era: " + numeroAleatorio);
                this.melhorJogador.perdePontos(1);
            }

            // Incrementar o número de jogadas
            this.numeroJogadas++;

            // Perguntar se o jogador deseja jogar novamente
            System.out.println("Deseja jogar novamente? (s/n)");
            String continuar = scanner.nextLine().toLowerCase();
            if (!continuar.equals("s")) {
                break;
            }
        }
    }

    // Método privado para gerar a jogada do computador
    private String gerarJogadaComputador() {
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int indice = (int) (Math.random() * opcoes.length);
        return opcoes[indice];
    }

    // Método privado para verificar o resultado do jogo
    private String verificarResultado(String jogadaJogador, String jogadaComputador) {
        if (jogadaJogador.equals(jogadaComputador)) {
            return "Empate!";
        } else if ((jogadaJogador.equals("pedra") && jogadaComputador.equals("tesoura")) ||
                (jogadaJogador.equals("papel") && jogadaComputador.equals("pedra")) ||
                (jogadaJogador.equals("tesoura") && jogadaComputador.equals("papel"))) {
            return "Você ganhou!";
        } else {
            return "Você perdeu!";
        }
    }

    // Método setter para atribuir o melhor jogador
    public void setMelhorJogador(Jogador jogador) {
        this.melhorJogador = jogador;
    }
}
