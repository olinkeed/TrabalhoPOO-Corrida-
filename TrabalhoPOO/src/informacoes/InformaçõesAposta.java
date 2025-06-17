package informacoes;

import java.util.Scanner;

/**
 * Classe abstrata que representa as informações relacionadas a uma aposta em uma corrida de animais.
 * Fornece métodos para gerenciar a escolha do animal, o valor da aposta e o resultado da aposta.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public abstract class InformaçõesAposta {
    Scanner sc = new Scanner(System.in);

    protected int animalEscolhido;
    protected double valorAposta;

    /**
     * Construtor da classe InformaçõesAposta.
     *
     * @param animalEscolhido O índice do animal escolhido para a aposta.
     * @param valorAposta O valor da aposta feita pelo usuário.
     */
    public InformaçõesAposta(int animalEscolhido, double valorAposta) {
        this.animalEscolhido = animalEscolhido;
        this.valorAposta = valorAposta;
    }

    /**
     * Retorna o índice do animal escolhido para a aposta.
     *
     * @return O índice do animal escolhido.
     */
    public abstract int getAnimalEscolhido();

    /**
     * Lê a escolha do animal feita pelo usuário.
     */
    public abstract void lerAnimal();

    /**
     * Subtrai o valor da aposta do saldo do usuário.
     */
    public abstract void subtraiAposta();

    /**
     * Define o valor da aposta.
     */
    public abstract void setValorAposta();

    /**
     * Verifica se o usuário ganhou ou perdeu a aposta.
     */
    public abstract void ganhouPerdeu();

    /**
     * Retorna o prêmio recebido pelo usuário em caso de vitória.
     *
     * @return O valor do prêmio.
     */
    public abstract double recebePremio();

    /**
     * Retorna o prêmio recebido pelo usuário em caso de empate.
     *
     * @return O valor do prêmio em caso de empate.
     */
    public abstract double recebePremioEmpate();
}
