package corrida;

import animais.Galinha;
import bancodedados.BancoDeDados;
import dados.DadosGalinha;
import informacoes.InformaçõesApostaGalinha;
import login.Login;
import menuapp.MenuApp;

import java.util.List;
import java.util.Random;

public class CorridaGalinha implements Corrida {
    Random random = new Random();

    private DadosGalinha dados;
    private InformaçõesApostaGalinha infoGalinha;
    private List<Galinha> galinhas;
    private Login login;

    private String mapa[][] = new String[6][100];
    private int[] anda = new int[6];

    public CorridaGalinha() {
    }

    public CorridaGalinha(DadosGalinha dados, Login login, InformaçõesApostaGalinha infoGalinha) {
        this.dados = dados;
        this.galinhas = dados.getGalinhas();
        this.login = login;
        this.infoGalinha = infoGalinha;
    }

    public void iniciaMapa() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 100; j++) {
                mapa[i][j] = "    ";
            }
        }
    }

    public void getMapa() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 100; j++) {
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
        infoGalinha.lerAnimal();
        infoGalinha.setValorAposta();
    }

    public void iniciaCorrida() {
        iniciaMapa();
        for (int i = 0; i < 6; i++) {
            mapa[i][0] = Integer.toString(i + 1) + "\uD83D\uDC14";
            anda[i] = 0;
        }
        getMapa();
    }

    public void corrida() {
        iniciaCorrida();
        while (termina()) {
            for (int i = 0; i < 6; i++) {
                if(random.nextInt(100) < galinhas.get(i).getVelocidade()){
                    continue;
                }else {
                    mapa[i][anda[i]] = "    ";
                    int passo = random.nextInt(galinhas.get(i).getVelocidade()) + 1;
                    anda[i] = Math.min(anda[i] + passo, 99);
                    mapa[i][anda[i]] = (i + 1) + "\uD83D\uDC14 ";
                }
            }
            getMapa();
        }
        empate();
        vencedor();
        infoGalinha.ganhouPerdeu();
    }

    public boolean termina() {
        boolean termina = true;
        for (int i = 0; i < 6; i++) {
            if (anda[i] >= 99) {
                termina = false;
            }
        }
        return termina;
    }

    public void vencedor() {
        for (int i = 0; i < 6; i++) {
            if (anda[i] == 99) {
                infoGalinha.setGalinhaVencedor(i + 1);
            }
        }
    }

    public void empate() {
        int contador = 0;
        for (int i = 0; i < 6; i++) {
            if (anda[i] >= 99) {
                contador++;
            }
        }
        if (contador >= 2 && anda[infoGalinha.getAnimalEscolhido() - 1] >= 99) {
            infoGalinha.setEmpate(true);
        }else {
            infoGalinha.setEmpate(false);
        }
    }

}
