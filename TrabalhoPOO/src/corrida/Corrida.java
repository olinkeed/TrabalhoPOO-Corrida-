package corrida;

/**
 * Interface que define os métodos essenciais para implementar uma corrida.
 * Cada tipo de corrida deve implementar essa interface para garantir a
 * funcionalidade básica de uma corrida de animais.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public interface Corrida {

    /**
     * Inicializa o mapa da corrida, preparando o ambiente para o início da competição.
     */
    public void iniciaMapa();

    /**
     * Exibe o mapa atual da corrida, mostrando a posição dos competidores.
     */
    public void getMapa();

    /**
     * Permite que o usuário faça apostas na corrida.
     */
    public void aposta();

    /**
     * Configura o estado inicial da corrida, posicionando os competidores no ponto de partida.
     */
    public void iniciaCorrida();

    /**
     * Executa o processo da corrida, movimentando os competidores até o final.
     */
    public void corrida();

    /**
     * Verifica se a corrida terminou.
     *
     * @return true se a corrida ainda está em andamento, false se já terminou.
     */
    public boolean termina();

    /**
     * Determina e define o vencedor da corrida.
     */
    public void vencedor();

    /**
     * Verifica e gerencia possíveis empates na corrida.
     */
    public void empate();
}
