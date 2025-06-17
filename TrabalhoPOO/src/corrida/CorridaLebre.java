package corrida;

import animais.Lebre;
import bancodedados.BancoDeDados;
import dados.DadosLebre;
import informacoes.InformaçõesApostaLebre;
import login.Login;
import menuapp.MenuApp;

import java.util.List;
import java.util.Random;

public class CorridaLebre implements Corrida {
    Random random = new Random();

    private DadosLebre dados;
    private InformaçõesApostaLebre infoLebre;
    private List<Lebre> lebres;
    private Login login;

    private String mapa[][] = new String[4][50];
    private int[] anda = new int[4];

    public CorridaLebre() {
    }

    public CorridaLebre(DadosLebre dados, Login login, InformaçõesApostaLebre infoLebre) {
        this.dados = dados;
        this.lebres = dados.getLebres();
        this.login = login;
        this.infoLebre = infoLebre;
    }

    public void iniciaMapa() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 50; j++) {
                mapa[i][j] = "    ";
            }
        }
    }

    public void getMapa() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print("|" + mapa[i][j] + "");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public void aposta() {
        if(BancoDeDados.buscarPontuacao(login.getNome()) <= 0){
            System.out.println("Você nao tem saldo para apostar, crie outro usuário!");
            MenuApp.menuMostrar();
            return;
        }
        dados.mostrarAnimais();
        infoLebre.lerAnimal();
        infoLebre.setValorAposta();
    }

    public void iniciaCorrida() {
        iniciaMapa();
        for (int i = 0; i < 4; i++) {
            mapa[i][0] = Integer.toString(i + 1) + "\uD83D\uDC07";
            anda[i] = 0;
        }
        getMapa();
    }

    public void corrida() {
        iniciaCorrida();
        while (termina()) {
            for (int i = 0; i < 4; i++) {
                if(random.nextInt(100) < lebres.get(i).getVelocidade()){
                    continue;
                }else {
                    mapa[i][anda[i]] = "    ";
                    int passo = random.nextInt(lebres.get(i).getVelocidade()) + 1;
                    anda[i] = Math.min(anda[i] + passo, 49);
                    mapa[i][anda[i]] = (i + 1) + "\uD83D\uDC07 ";
                }
            }
            getMapa();
        }
        empate();
        vencedor();
        infoLebre.ganhouPerdeu();
    }

    public boolean termina() {
        boolean termina = true;
        for (int i = 0; i < 4; i++) {
            if (anda[i] >= 49) {
                termina = false;
            }
        }
        return termina;
    }

    public void vencedor() {
        for (int i = 0; i < 4; i++) {
            if (anda[i] == 49) {
                infoLebre.setLebreVencedor(i + 1);
            }
        }
    }

    public void empate() {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            if (anda[i] >= 49) {
                contador++;
            }
        }
        if (contador >= 2 && anda[infoLebre.getAnimalEscolhido() - 1] >= 49) {
            infoLebre.setEmpate(true);
        }else {
            infoLebre.setEmpate(false);
        }
    }

}
