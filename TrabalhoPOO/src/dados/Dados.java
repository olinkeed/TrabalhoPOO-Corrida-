package dados;

import animais.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe abstrata que serve como base para a manipulação de dados de animais em corridas.
 * Fornece métodos para montar animais e gerar suas velocidades.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public abstract class Dados {
    protected Random random = new Random();

    /**
     * Método abstrato que deve ser implementado para montar a lista de animais.
     */
    public abstract void montarAnimais();

    /**
     * Método abstrato que deve ser implementado para gerar a velocidade dos animais.
     */
    public abstract void gerarVelocidade();

    public abstract void mostrarAnimais();
}
