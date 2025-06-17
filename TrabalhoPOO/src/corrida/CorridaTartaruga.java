package corrida;

import animais.Tartaruga;
import bancodedados.BancoDeDados;
import dados.DadosTartaruga;
import informacoes.InformaçõesApostaTartaruga;
import login.Login;
import menuapp.MenuApp;

import java.util.List;
import java.util.Random;

public class CorridaTartaruga implements Corrida {
    Random random = new Random();

    private DadosTartaruga dados;
    private InformaçõesApostaTartaruga infoTartaruga;
    private List<Tartaruga> tartarugas;
    private Login login;

    private String mapa[][] = new String[3][30];
    private int[] anda = new int[3];

    public CorridaTartaruga() {
    }

    public CorridaTartaruga(DadosTartaruga dados, Login login, InformaçõesApostaTartaruga infoTartaruga) {
        this.dados = dados;
        this.tartarugas = dados.getTartarugas();
        this.login = login;
        this.infoTartaruga = infoTartaruga;
    }

    public void iniciaMapa() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 30; j++) {
                mapa[i][j] = "    ";
            }
        }
    }

    public void getMapa() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 30; j++) {
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
        infoTartaruga.lerAnimal();
        infoTartaruga.setValorAposta();
    }

    public void iniciaCorrida() {
        iniciaMapa();
        for (int i = 0; i < 3; i++) {
            mapa[i][0] = Integer.toString(i + 1) + "\uD83D\uDC22";
            anda[i] = 0;
        }
        getMapa();
    }

    public void corrida() {
        iniciaCorrida();
        while (termina()) {
            for (int i = 0; i < 3; i++) {
                if(random.nextInt(100) < tartarugas.get(i).getVelocidade()){
                    continue;
                }else {
                    mapa[i][anda[i]] = "    ";
                    int passo = random.nextInt(tartarugas.get(i).getVelocidade()) + 1;
                    anda[i] = Math.min(anda[i] + passo, 29);
                    mapa[i][anda[i]] = (i + 1) + "\uD83D\uDC22 ";
                }
            }
            getMapa();
        }
        empate();
        vencedor();
        infoTartaruga.ganhouPerdeu();
    }

    public boolean termina() {
        boolean termina = true;
        for (int i = 0; i < 3; i++) {
            if (anda[i] >= 29) {
                termina = false;
            }
        }
        return termina;
    }

    public void vencedor() {
        for (int i = 0; i < 3; i++) {
            if (anda[i] == 29) {
                infoTartaruga.setTartarugaVencedor(i + 1);
            }
        }
    }

    public void empate() {
        int contador = 0;
        for (int i = 0; i < 3; i++) {
            if (anda[i] >= 29) {
                contador++;
            }
        }
        if (contador >= 2 && anda[infoTartaruga.getAnimalEscolhido() - 1] >= 29) {
            infoTartaruga.setEmpate(true);
        }else {
            infoTartaruga.setEmpate(false);
        }
    }

}
