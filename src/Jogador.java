/*[M1S05] Ex 1 - Crie a Classe Jogador

Vamos criar um jogo simples em Java!
Primeiramente crie a Classe Jogador.
Cada vez que um jogo for instanciado devemos ter um novo objeto jogador.
O Jogador deve ter os atributos: Nome, idade, pontuação, numeroTentivas.
O Jogador deve ter os métodos: adicionaPontos, perdePontos, adicionaTentativa.
 */

/*[M1S05] EX 2 - Adicione encapsulamento ao Jogador
Todos os atributos devem estar como private.

Devemos ter um construtor que recebe todos os dados do jogador.
E devemos ter getters e setters para esse jogador.
 */

public class Jogador {
    private String nome;            // Nome do jogador
    private int idade;              // Idade do jogador
    private int pontuacao;          // Pontuação do jogador
    private int numeroTentativas;   // Número de tentativas do jogador

    // Construtor da classe Jogador
    public Jogador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = 0;             // Inicializa a pontuação do jogador como zero
        this.numeroTentativas = 0;      // Inicializa o número de tentativas do jogador como zero
    }

    // Métodos para acessar as informações do jogador

    // Retorna o nome do jogador
    public String getNome() {
        return nome;
    }

    // Retorna a idade do jogador
    public int getIdade() {
        return idade;
    }

    // Retorna a pontuação do jogador
    public int getPontuacao() {
        return pontuacao;
    }

    // Retorna o número de tentativas do jogador
    public int getNumeroTentativas() {
        return numeroTentativas;
    }

    // Método para adicionar pontos à pontuação do jogador
    public void adicionaPontos(int pontos) {
        this.pontuacao += pontos;
    }

    // Método para subtrair pontos da pontuação do jogador
    // A pontuação não pode ser negativa, então é utilizada a função Math.max para garantir isso
    public void perdePontos(int pontos) {
        this.pontuacao = Math.max(0, this.pontuacao - pontos);
    }

    // Método para adicionar uma tentativa ao número de tentativas do jogador
    public void adicionaTentativa() {
        this.numeroTentativas++;
    }
}

