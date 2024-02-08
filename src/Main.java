/*[M1S05] EX 3 - Crie uma lista de melhores Jogadores

Devemos ter uma lista dos melhores jogadores.
Essa lista terá os objetos de cada jogador, e a posição deles na lista reflete o ranking desse jogador.
 */
/*[M1S05] EX 4 - Ranking de Jogadores

A lista de jogadores é exibida ao final de cada jogo, se a lista tiver mais de 10 jogares devem aparecer apenas os top 10 jogadores.
Ao exibir a lista o jogador deve ter um print ao final com o seu nome e a sua posição. A lista deve ser exibida da seguinte forma:
nome do jogador - posição
 */
/*[M1S05] EX 5 - Valide se o jogador já existe

Sempre que um jogador for criado, adicione uma validação se o nome do jogador existe na lista de jogadores.
Se ele já existir, peça para ele colocar outro nome, e isso deve rodar em loop.
 */
/*[M1S05] EX 8 - Main e loop principal

Crie na classe Main, no método main, a lógica de escolha dos jogos, ele deve rodar em um loop infinito. O jogador deve ou se identificar pelo nome, ou deve criar um novo jogador.
O jogador deve poder escolher entre os jogos criados anteriormente, e ao final do jogo ele deve ter as opções: Ver ranking completo, ver os 10 maiores, jogar novamente ou sair do jogo.
As escolhas podem ser por números ou por letras, seguindo o seguinte exemplo:

Qual das seguintes opções você deseja seguir:
1 - Ver ranking completo

2 - Ver top 10

3 - Jogar novamente

4 - Encerrar o Jogo

Opção escolhida: <Numero escolhido pelo jogador>
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> listaJogadores = new ArrayList<>();
        Jogo jogoPedraPapelTesoura = new Jogo();
        Jogo jogoAdivinharNumero = new Jogo();
        Jogador jogadorAtual = null; // Variável para armazenar o jogador atual

        // Loop principal do jogo
        while (true) {
            System.out.println("Bem-vindo ao Jogo!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Identificar-se ou criar novo jogador");
            System.out.println("2 - Jogar Pedra, Papel e Tesoura");
            System.out.println("3 - Jogar Adivinhar o Número");
            System.out.println("4 - Ver ranking completo");
            System.out.println("5 - Ver top 10");
            System.out.println("6 - Sair do jogo");

            int opcao;
            // Verificar se o próximo token é um número
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha após a leitura do número
            } else {
                System.out.println("Opção inválida! Por favor, escolha uma opção de 1 a 6.");
                scanner.nextLine(); // Consumir a entrada inválida
                continue; // Reiniciar o loop para pedir outra entrada válida
            }

            switch (opcao) {
                case 1:
                    jogadorAtual = identificarOuCriarJogador(scanner, listaJogadores);
                    break;
                case 2:
                    if (jogadorAtual == null) {
                        System.out.println("Você precisa se identificar ou criar um novo jogador primeiro.");
                        break; // Voltar ao menu principal
                    }
                    jogoPedraPapelTesoura.setMelhorJogador(jogadorAtual); //Pontuação atribuida ao jogador correto
                    jogoPedraPapelTesoura.jogarPedraPapelTesoura(listaJogadores);
                    break;
                case 3:
                    if (jogadorAtual == null) {
                        System.out.println("Você precisa se identificar ou criar um novo jogador primeiro.");
                        break; // Voltar ao menu principal
                    }
                    jogoAdivinharNumero.setMelhorJogador(jogadorAtual); //Pontuação atribuida ao jogador correto
                    System.out.println("Digite o limite superior para o jogo de Adivinhar o Número:");
                    int limiteSuperior;
                    // Verificar se o próximo token é um número
                    if (scanner.hasNextInt()) {
                        limiteSuperior = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número
                    } else {
                        System.out.println("Valor inválido! Por favor, digite um número.");
                        scanner.nextLine(); // Consumir a entrada inválida
                        break; // Voltar ao menu principal
                    }
                    jogoAdivinharNumero.jogarAdivinharNumero(listaJogadores, scanner, limiteSuperior);
                    break;
                case 4:
                    // Exibir o ranking completo dos jogadores
                    exibirRankingCompleto(listaJogadores);
                    break;
                case 5:
                    // Exibir o top 10 dos jogadores
                    exibirTop10(listaJogadores);
                    break;
                case 6:
                    // Encerrar o jogo
                    System.out.println("Obrigado por jogar! Até mais.");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    // Método para identificar ou criar um novo jogador
    private static Jogador identificarOuCriarJogador(Scanner scanner, List<Jogador> listaJogadores) {
        System.out.println("Digite o nome do jogador:");
        String nomeJogador = scanner.nextLine();
        // Verificar se o nome contém apenas letras
        if (!nomeJogador.matches("[a-zA-Z]+")) {
            System.out.println("Nome inválido! O nome deve conter apenas letras.");
            return null; // Reiniciar o loop para pedir outra entrada válida
        }
        System.out.println("Digite a idade do jogador:");
        int idadeJogador;
        try {
            idadeJogador = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após a leitura da idade
        } catch (InputMismatchException e) {
            System.out.println("Idade inválida! A idade deve ser um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
            return null; // Reiniciar o loop para pedir outra entrada válida
        }
        // Verifica se o jogador já está na lista. Se estiver, ele o recebe de volta e exibe uma mensagem de boas-vindas.
        // Se não estiver, um novo jogador com o nome fornecido pelo usuário é adicionado à lista.
        // Não consegui fazer sozinha
        boolean jogadorExistente = listaJogadores.stream().anyMatch(j -> j.getNome().equals(nomeJogador));
        if (jogadorExistente) {
            System.out.println("Bem-vindo de volta, " + nomeJogador + "!");
            return listaJogadores.stream().filter(j -> j.getNome().equals(nomeJogador)).findFirst().orElse(null);
        } else {
            // Adicionar um novo jogador à lista de jogadores
            System.out.println("Jogador " + nomeJogador + " criado com sucesso!");
            Jogador novoJogador = new Jogador(nomeJogador, idadeJogador);
            listaJogadores.add(novoJogador);
            return novoJogador;
        }
    }

    // Método para exibir o ranking completo dos jogadores
    private static void exibirRankingCompleto(List<Jogador> listaJogadores) {
        System.out.println("Ranking Completo:");
        // Ordenar a lista de jogadores pelo número de pontos em ordem decrescente
        listaJogadores.sort((jogador1, jogador2) -> jogador2.getPontuacao() - jogador1.getPontuacao());
        for (int i = 0; i < listaJogadores.size(); i++) {
            Jogador jogador = listaJogadores.get(i);
            System.out.println((i + 1) + " - " + jogador.getNome() + " - Pontuação: " + jogador.getPontuacao());
        }
    }

    // Método para exibir o top 10 dos jogadores
    private static void exibirTop10(List<Jogador> listaJogadores) {
        System.out.println("Top 10:");
        // Ordenar a lista de jogadores pelo número de pontos em ordem decrescente
        listaJogadores.sort((jogador1, jogador2) -> jogador2.getPontuacao() - jogador1.getPontuacao());
        int totalJogadores = Math.min(10, listaJogadores.size());
        for (int i = 0; i < totalJogadores; i++) {
            Jogador jogador = listaJogadores.get(i);
            System.out.println((i + 1) + " - " + jogador.getNome() + " - Pontuação: " + jogador.getPontuacao());
        }
    }
}
