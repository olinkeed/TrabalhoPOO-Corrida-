package dados;

import animais.Tartaruga;

import java.util.ArrayList;
import java.util.List;

public class DadosTartaruga extends Dados{
    private List<Tartaruga> tartarugas = new ArrayList<>();
    int a = 1;

    public DadosTartaruga() {
        montarAnimais();
    }


    @Override
    public void montarAnimais(){
        tartarugas.add(new Tartaruga("Casco Supersônico"));
        tartarugas.add(new Tartaruga("Turbolenta"));
        tartarugas.add(new Tartaruga("Devagar e Sempre"));
    }

    @Override
    public void gerarVelocidade() {
        for (Tartaruga tartaruga : tartarugas) {
            int velocidade = random.nextInt(3) + 3;
            tartaruga.setVelocidade(velocidade);
        }

        for (Tartaruga tartaruga : tartarugas) {
            if (tartaruga.getVelocidade() == 3) {
                tartaruga.setOdd(4);
            } else if (tartaruga.getVelocidade() == 4) {
                tartaruga.setOdd(3);
            } else if (tartaruga.getVelocidade() == 5) {
                tartaruga.setOdd(2);
            }
        }
    }

    public List<Tartaruga> getTartarugas() {
        return tartarugas;
    }

    @Override
    public void mostrarAnimais() {
        gerarVelocidade();
        System.out.println();
        for (Tartaruga tartaruga : tartarugas) {
            System.out.println("Tartaruga " + a + ": " + tartaruga.getNome() + " Velocidade Maxima: " + tartaruga.getVelocidade() + " Multiplicação da aposta: " + tartaruga.getOdd());
            a++;
        }
    }
}
