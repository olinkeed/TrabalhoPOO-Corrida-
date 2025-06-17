package corrida;

import animais.Cavalo;
import bancodedados.BancoDeDados;
import dados.DadosCavalo;
import informacoes.InformaçõesApostaCavalo;
import login.Login;
import menuapp.MenuApp;

import java.util.List;
import java.util.Random;

public class CorridaCavalo implements Corrida {
    Random random = new Random();

    private DadosCavalo dados;
    private InformaçõesApostaCavalo infoCavalo;
    private List<Cavalo> cavalos;
    private Login login;

    private String mapa[][] = new String[7][125];
    private int[] anda = new int[7];

    public CorridaCavalo() {
    }

    public CorridaCavalo(DadosCavalo dados, Login login, InformaçõesApostaCavalo infoCavalo) {
        this.dados = dados;
        this.cavalos = dados.getCavalos();
        this.login = login;
        this.infoCavalo = infoCavalo;
    }

    public void iniciaMapa() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 125; j++) {
                mapa[i][j] = "    ";
            }
        }
    }

    public void getMapa() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 125; j++) {
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
        infoCavalo.lerAnimal();
        infoCavalo.setValorAposta();
    }

    public void iniciaCorrida() {
        iniciaMapa();
        for (int i = 0; i < 7; i++) {
            mapa[i][0] = Integer.toString(i + 1) + "\uD83D\uDC0E";
            anda[i] = 0;
        }
        getMapa();
    }

    public void corrida() {
        iniciaCorrida();
        while (termina()) {
            for (int i = 0; i < 7; i++) {
                if(random.nextInt(100) < cavalos.get(i).getVelocidade()){
                    continue;
                }else {
                    mapa[i][anda[i]] = "    ";
                    int passo = random.nextInt(cavalos.get(0).getVelocidade()) + 1;
                    anda[i] = Math.min(anda[i] + passo, 124);
                    mapa[i][anda[i]] = (i + 1) + "\uD83D\uDC0E ";
                }
            }
            getMapa();
        }
        empate();
        vencedor();
        infoCavalo.ganhouPerdeu();
    }

    public boolean termina() {
        boolean termina = true;
        for (int i = 0; i < 7; i++) {
            if (anda[i] >= 124) {
                termina = false;
            }
        }
        return termina;
    }

    public void vencedor() {
        for (int i = 0; i < 7; i++) {
            if (anda[i] == 124) {
                infoCavalo.setCavaloVencedor(i + 1);
            }
        }
    }

    public void empate() {
        int contador = 0;
        for (int i = 0; i < 7; i++) {
            if (anda[i] >= 124) {
                contador++;
            }
        }
        if (contador >= 2 && anda[infoCavalo.getAnimalEscolhido() - 1] >= 124) {
            infoCavalo.setEmpate(true);
        }else {
            infoCavalo.setEmpate(false);
        }
    }

}
