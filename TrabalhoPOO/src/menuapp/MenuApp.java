package menuapp;

import bancodedados.BancoDeDados;
import corrida.Corrida;
import login.EscolhaJogo;
import login.Login;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que gerencia o menu do jogo de corrida de animais.
 * Permite ao usuário interagir com o sistema, escolher jogos e visualizar informações.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class MenuApp {
    static Scanner sc = new Scanner(System.in);
    static Login login = new Login();
    static EscolhaJogo jogo = new EscolhaJogo();
    static Corrida corrida = null;

    /**
     * Lê o nome do jogador.
     */
    public static void lerPrimeiroNome() {
        login.lerNome();
    }

    /**
     * Exibe a pontuação atual do jogador.
     */
    public static void pontuacao() {
        System.out.println("Nome: " + login.getNome() + "            Pontuação: " + BancoDeDados.buscarPontuacao(login.getNome()));
    }

    /**
     * Exibe o menu principal do jogo.
     */
    public static void menuMostrar() {
        pontuacao();
        System.out.println("1 - Jogar");
        System.out.println("2 - Ranking");
        System.out.println("3 - Mudar usuário");
        System.out.println("4 - Informações");
        System.out.println("0 - Sair");
        menuSelecionado();
    }

    /**
     * Lê a seleção do menu pelo usuário.
     */
    public static void menuSelecionado() {
        int num = 0;
        while (true) {
            try {
                num = sc.nextInt();

                if (num >= 0 && num <= 4) {
                    break;
                } else {
                    System.out.println("Digite um número válido!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                sc.nextLine();
            }
        }

        switch (num) {
            case 1:
                jogar();
                continuar();
                break;
            case 2:
                System.out.println();
                BancoDeDados.exibirRanking();
                System.out.println();
                menuMostrar();
                break;
            case 3:
                System.out.println();
                trocarUsuario();
                break;
            case 4:
                mostrarInformacoesJogo();
                menuMostrar();
                break;
            case 0:
                System.out.println("Encerrando o programa. Até logo!");
                System.exit(0);
                break;
        }
    }

    /**
     * Permite ao usuário trocar de usuário.
     */
    public static void trocarUsuario() {
        login.lerNome();
        menuMostrar();
    }

    /**
     * Inicia o jogo e permite que o usuário faça uma aposta.
     */
    public static void jogar() {
        corrida = jogo.escolheJogo(login);
        corrida.aposta();
        corrida.corrida();
        pontuacao();
    }

    /**
     * Exibe informações sobre o jogo de corrida de animais.
     */
    public static void mostrarInformacoesJogo() {
        System.out.println();
        System.out.println("📜 Sobre o Jogo: Corrida de Animais 🐾🏁\n");
        System.out.println("Bem-vindo ao Corrida Animal Bet! 🎲🏇🐢🐔🐶🐇\n");

        System.out.println("Aqui você poderá apostar em diferentes tipos de corridas malucas com os mais variados animais! 🏃‍♂️💨\n");

        System.out.println("Cada corrida tem seus próprios competidores e regras de velocidade:");
        System.out.println("- 🐎 Corrida de Cavalos: 7 cavalos velozes disputando o topo da pista!");
        System.out.println("- 🐔 Corrida de Galinhas: 6 galinhas agitadas, correndo pelo prêmio!");
        System.out.println("- 🐶 Corrida de Cachorros: 5 cachorros mostrando quem é o mais rápido!");
        System.out.println("- 🐇 Corrida de Lebres: 4 lebres saltitantes brigando pelo pódio!");
        System.out.println("- 🐢 Corrida de Tartarugas: Apenas 3... mas com muita determinação!\n");

        System.out.println("🎯 Como funciona a aposta?\n");

        System.out.println("👉 Antes de cada corrida, você escolhe em qual animal quer apostar e o valor da sua aposta.");
        System.out.println("👉 A odd (multiplicador de ganho) é calculada com base na velocidade máxima de cada animal:");
        System.out.println("- Quanto mais lento o animal, maior a recompensa se ele vencer! 🥇💰");
        System.out.println("- Animais rápidos têm odds menores, mas mais chances de ganhar. 😉\n");

        System.out.println("💡 Exemplo de lógica das odds:");
        System.out.println("- Um cavalo com alta velocidade? Odd baixa (favorito).");
        System.out.println("- Uma tartaruga azarona? Odd alta (mas o prêmio compensa o risco!).\n");

        System.out.println("📌 Regras básicas:");
        System.out.println("- ✅ Escolha a corrida.");
        System.out.println("- ✅ Escolha o animal.");
        System.out.println("- ✅ Defina o valor da aposta.");
        System.out.println("- ✅ Torça e acompanhe a corrida em tempo real!\n");

        System.out.println("Boa sorte! 🍀 Que vença o mais rápido... ou o mais sortudo! 😄\n");
    }

    /**
     * Permite ao usuário continuar jogando ou retornar ao menu.
     */
    public static void continuar() {
        int opcao = 3;

        System.out.println("1 - Continuar Jogando");
        System.out.println("2 - Ir para o Menu");
        System.out.println("0 - Sair");
        while (true) {
            try {
                opcao = sc.nextInt();

                if (opcao >= 0 && opcao <= 2) {
                    break;
                } else {
                    System.out.println("Digite um número válido de 0 a 2!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                sc.nextLine();
            }
        }

        if (opcao == 1) {
            System.out.println();
            jogar();
            continuar();
        } else if (opcao == 2) {
            System.out.println();
            menuMostrar();
        } else if (opcao == 0) {
            System.out.println();
            System.out.println("Encerrando o programa. Até logo!");
            System.exit(0);
        } else {
            System.out.println("Digite um número válido!");
        }
    }
}
