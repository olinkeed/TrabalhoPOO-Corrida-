package corrida;

import animais.Cachorro;
import bancodedados.BancoDeDados;
import dados.DadosCachorro;
import informacoes.InformaçõesApostaCachorro;
import login.Login;
import menuapp.MenuApp;

import java.util.List;
import java.util.Random;

/**
 * Classe que representa uma corrida de cachorros.
 * Implementa a interface Corrida.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class CorridaCachorro implements Corrida {
    Random random = new Random();

    private DadosCachorro dados;
    private InformaçõesApostaCachorro infoCachorro;
    private List<Cachorro> cachorros;
    private Login login;

    private String mapa[][] = new String[5][75];
    private int[] anda = new int[5];

    /**
     * Construtor padrão da classe CorridaCachorro.
     */
    public CorridaCachorro() {
    }

    /**
     * Construtor da classe CorridaCachorro com parâmetros.
     *
     * @param dados       Os dados dos cachorros.
     * @param login       O objeto de login.
     * @param infoCachorro As informações da aposta do cachorro.
     */
    public CorridaCachorro(DadosCachorro dados, Login login, InformaçõesApostaCachorro infoCachorro) {
        this.dados = dados;
        this.cachorros = dados.getCachorros();
        this.login = login;
        this.infoCachorro = infoCachorro;
    }

    /**
     * Inicializa o mapa da corrida.
     */
    public void iniciaMapa() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 75; j++) {
                mapa[i][j] = "    ";
            }
        }
    }

    /**
     * Exibe o mapa da corrida.
     */
    public void getMapa() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 75; j++) {
                System.out.print("|" + mapa[i][j] + "");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    /**
     * Permite que o jogador faça uma aposta.
     */
    public void aposta() {
        if(BancoDeDados.buscarPontuacao(login.getNome()) <= 0){
            System.out.println("Você nao tem saldo para apostar, crie outro usuário!");
            MenuApp.menuMostrar();
            return;
        }
        dados.mostrarAnimais();
        infoCachorro.lerAnimal();
        infoCachorro.setValorAposta();
    }

    /**
     * Inicia a corrida, configurando o mapa e a posição inicial dos cachorros.
     */
    public void iniciaCorrida() {
        iniciaMapa();
        for (int i = 0; i < 5; i++) {
            mapa[i][0] = Integer.toString(i + 1) + "\uD83D\uDC15";
            anda[i] = 0;
        }
        getMapa();
    }

    /**
     * Executa a corrida, movendo os cachorros e verificando o vencedor.
     */
    public void corrida() {
        iniciaCorrida();
        while (termina()) {
            for (int i = 0; i < 5; i++) {
                if (random.nextInt(100) < cachorros.get(i).getVelocidade()) {
                    continue;
                } else {
                    mapa[i][anda[i]] = "    ";
                    int passo = random.nextInt(cachorros.get(i).getVelocidade()) + 1;
                    anda[i] = Math.min(anda[i] + passo, 74);
                    mapa[i][anda[i]] = (i + 1) + "\uD83D\uDC15 ";
                }
            }
            getMapa();
        }
        empate();
        vencedor();
        infoCachorro.ganhouPerdeu();
    }

    /**
     * Verifica se a corrida deve continuar.
     *
     * @return true se a corrida deve continuar, false se terminou.
     */
    public boolean termina() {
        boolean termina = true;
        for (int i = 0; i < 5; i++) {
            if (anda[i] >= 74) {
                termina = false;
            }
        }
        return termina;
    }

    /**
     * Determina o vencedor da corrida.
     */
    public void vencedor() {
        for (int i = 0; i < 5; i++) {
            if (anda[i] == 74) {
                infoCachorro.setCachorroVencedor(i + 1);
            }
        }
    }

    /**
     * Verifica se houve empate na corrida.
     */
    public void empate() {
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            if (anda[i] >= 74) {
                contador++;
            }
        }
        if (contador >= 2 && anda[infoCachorro.getAnimalEscolhido() - 1] >= 74) {
            infoCachorro.setEmpate(true);
        } else {
            infoCachorro.setEmpate(false);
        }
    }
}
