package animais;

/**
 * Classe abstrata que representa um animal participante de uma corrida.
 * Contém informações sobre o nome, velocidade máxima e odds do animal.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public abstract class Animal {
    private String nome;
    private int maxVelocidade;
    private double odd;

    /**
     * Construtor padrão da classe Animal.
     */
    public Animal() {
    }

    /**
     * Construtor da classe Animal que inicializa o nome do animal.
     *
     * @param nome O nome do animal.
     */
    public Animal(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome do animal.
     *
     * @return O nome do animal.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a velocidade máxima do animal.
     *
     * @return A velocidade máxima do animal.
     */
    public int getVelocidade() {
        return maxVelocidade;
    }

    /**
     * Define a velocidade máxima do animal.
     *
     * @param velocidade A velocidade máxima a ser definida.
     */
    public void setVelocidade(int velocidade) {
        this.maxVelocidade = velocidade;
    }

    /**
     * Retorna a odd do animal.
     *
     * @return A odd do animal.
     */
    public double getOdd() {
        return odd;
    }

    /**
     * Define a odd do animal.
     *
     * @param odd A odd a ser definida.
     */
    public void setOdd(double odd) {
        this.odd = odd;
    }
}
