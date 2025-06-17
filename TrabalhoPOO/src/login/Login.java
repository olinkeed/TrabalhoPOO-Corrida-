package login;

import bancodedados.BancoDeDados;

import java.util.Scanner;

/**
 * Classe que gerencia o login dos jogadores.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class Login {
    Scanner sc = new Scanner(System.in);

    private String nome;
    private double pontuacao;

    /**
     * Construtor padrão da classe Login.
     */
    public Login() {
    }

    /**
     * Retorna o nome do jogador.
     *
     * @return O nome do jogador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Verifica se o jogador já está registrado e exibe a pontuação.
     * Se for um novo jogador, registra-o com um saldo inicial de 1000 pontos.
     */
    public void verificaJogador() {
        double pontos = BancoDeDados.buscarPontuacao(nome);
        if (pontos > 0) {
            System.out.println("Bem-vindo de volta, " + nome + "! Você tem " + BancoDeDados.buscarPontuacao(nome) + " pontos.");
            System.out.println();
        } else {
            System.out.println("Novo jogador registrado! Você recebeu um saldo de 1000");
            System.out.println();
            BancoDeDados.salvarOuAtualizar(nome, 1000);
        }
    }

    /**
     * Lê o nome do jogador a partir da entrada do usuário.
     */
    public void lerNome() {
        System.out.println("Digite o seu nome: ");
        nome = (sc.nextLine());
        verificaJogador();
    }
}
