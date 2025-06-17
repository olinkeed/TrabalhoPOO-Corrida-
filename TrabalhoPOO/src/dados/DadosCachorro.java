package dados;

import animais.Cachorro;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia os dados dos cachorros para as corridas.
 * Extende a classe Dados.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class DadosCachorro extends Dados {
    private List<Cachorro> cachorros = new ArrayList<>();
    int a = 1;

    /**
     * Construtor da classe DadosCachorro.
     * Inicializa a lista de cachorros.
     */
    public DadosCachorro() {
        montarAnimais();
    }

    /**
     * Monta a lista de cachorros com nomes pré-definidos.
     */
    @Override
    public void montarAnimais() {
        cachorros.add(new Cachorro("Relâmpago Canino"));
        cachorros.add(new Cachorro("Bolt da Pista"));
        cachorros.add(new Cachorro("Pernas velozes"));
        cachorros.add(new Cachorro("Foguete Peludo"));
        cachorros.add(new Cachorro("Turbo Latido"));
    }

    /**
     * Gera a velocidade dos cachorros e define suas odds com base na velocidade.
     */
    @Override
    public void gerarVelocidade() {
        for (Cachorro cachorro : cachorros) {
            int velocidade = random.nextInt(5) + 4;
            cachorro.setVelocidade(velocidade);
        }

        for (Cachorro cachorro : cachorros) {
            if (cachorro.getVelocidade() == 4) {
                cachorro.setOdd(6);
            } else if (cachorro.getVelocidade() == 5) {
                cachorro.setOdd(4.5);
            } else if (cachorro.getVelocidade() == 6) {
                cachorro.setOdd(3);
            } else if (cachorro.getVelocidade() == 7) {
                cachorro.setOdd(2);
            } else if (cachorro.getVelocidade() == 8) {
                cachorro.setOdd(1.5);
            }
        }
    }

    /**
     * @return A lista de cachorros.
     */

    public List<Cachorro> getCachorros() {
        return cachorros;
    }

    /**
     * Mostra os cachorros, suas velocidades máximas e as odds.
     */
    @Override
    public void mostrarAnimais() {
        gerarVelocidade();
        System.out.println();
        for (Cachorro cachorro : cachorros) {
            System.out.println("Cachorro " + a + ": " + cachorro.getNome() + " Velocidade Máxima: " + cachorro.getVelocidade() + " Multiplicação da aposta: " + cachorro.getOdd());
            a++;
        }
    }
}
