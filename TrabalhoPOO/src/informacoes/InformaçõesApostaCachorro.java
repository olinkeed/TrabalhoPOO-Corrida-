package informacoes;

import animais.Cachorro;
import bancodedados.BancoDeDados;
import dados.DadosCachorro;
import login.Login;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que gerencia as informações de apostas em cachorros.
 * Extende a classe InformaçõesAposta.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class InformaçõesApostaCachorro extends InformaçõesAposta {
    Scanner sc = new Scanner(System.in);

    private DadosCachorro dados;
    private List<Cachorro> cachorros;
    private Login login;
    private int cachorroVencedor;
    private boolean empate = false;

    /**
     * Construtor da classe InformaçõesApostaCachorro.
     *
     * @param login O objeto de login do usuário.
     * @param dados Os dados dos cachorros disponíveis para aposta.
     */
    public InformaçõesApostaCachorro(Login login, DadosCachorro dados) {
        super(0, 0);
        this.login = login;
        this.dados = dados;
        cachorros = dados.getCachorros();
    }

    /**
     * Define se houve empate na corrida.
     *
     * @param empate true se houve empate, false caso contrário.
     */
    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    /**
     * Define o cachorro vencedor da corrida.
     *
     * @param cachorroVencedor O número do cachorro vencedor.
     */
    public void setCachorroVencedor(int cachorroVencedor) {
        this.cachorroVencedor = cachorroVencedor;
    }

    @Override
    public int getAnimalEscolhido() {
        return animalEscolhido;
    }

    /**
     * Solicita ao usuário o valor da aposta e valida a entrada.
     */
    @Override
    public void setValorAposta() {
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("Qual será o valor da aposta?");
                valorAposta = sc.nextDouble();

                if (valorAposta <= 0.0) {
                    System.out.println("O valor da aposta não pode ser <= 0, digite novamente o valor da aposta:");
                    continue;
                }

                if (valorAposta > BancoDeDados.buscarPontuacao(login.getNome())) {
                    System.out.println("O valor da aposta é maior que seu saldo, digite novamente o valor da aposta:");
                    continue;
                }

                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Use números para definir o valor da aposta.");
                sc.nextLine();
            }
        }
        subtraiAposta();
    }

    /**
     * Lê a escolha do cachorro em que o usuário deseja apostar.
     */
    @Override
    public void lerAnimal() {
        try {
            System.out.println();
            System.out.println("Escolha um cachorro de 1 a 5 que deseja apostar:");
            animalEscolhido = sc.nextInt();
            while (animalEscolhido < 1 || animalEscolhido > 5) {
                System.out.println("Escolha um cachorro de 1 a 5 que deseja apostar:");
                animalEscolhido = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            sc.nextLine();
            lerAnimal();
        }
    }

    /**
     * Subtrai o valor da aposta do saldo do usuário.
     */
    @Override
    public void subtraiAposta() {
        double pontuacao = BancoDeDados.buscarPontuacao(login.getNome()) - valorAposta;
        System.out.println("Pontuação: " + pontuacao);
        BancoDeDados.salvarOuAtualizar(login.getNome(), pontuacao);
    }

    /**
     * Verifica se o usuário ganhou ou perdeu a aposta e atualiza o saldo.
     */
    @Override
    public void ganhouPerdeu() {
        if (empate) {
            System.out.println("EMPATE! Você ganhou 25% do prêmio total");
            BancoDeDados.salvarOuAtualizar(login.getNome(), BancoDeDados.buscarPontuacao(login.getNome()) + recebePremioEmpate());
        } else if (cachorroVencedor == animalEscolhido) {
            System.out.println("Parabéns, você venceu!!");
            BancoDeDados.salvarOuAtualizar(login.getNome(), BancoDeDados.buscarPontuacao(login.getNome()) + recebePremio());
        } else {
            System.out.println("Hoje não é seu dia de sorte, você perdeu!");
            System.out.println();
        }
    }

    /**
     * Calcula o prêmio a ser recebido pelo usuário em caso de vitória.
     *
     * @return valor do prêmio calculado.
     */
    @Override
    public double recebePremio() {
        for (int i = 0; i < 5; i++) {
            if (animalEscolhido == (i + 1)) {
                System.out.println("SEU PRÊMIO FOI DE: " + valorAposta * cachorros.get(i).getOdd());
                System.out.println();
                return (valorAposta * cachorros.get(i).getOdd());
            }
        }
        return 0;
    }

    /**
     * Calcula o prêmio a ser recebido pelo usuário em caso de empate.
     *
     * @return valor do prêmio proporcional ao empate.
     */
    @Override
    public double recebePremioEmpate() {
        for (int i = 0; i < 5; i++) {
            if (getAnimalEscolhido() == (i + 1)) {
                System.out.println("SEU PRÊMIO FOI DE: " + (recebePremio() * 0.25));
                System.out.println();
                return (recebePremio() * 0.25);
            }
        }
        return 0;
    }
}

