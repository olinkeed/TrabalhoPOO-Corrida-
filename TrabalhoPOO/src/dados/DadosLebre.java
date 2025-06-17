package dados;

import animais.Lebre;

import java.util.ArrayList;
import java.util.List;

public class DadosLebre extends Dados{
    private List<Lebre> lebres = new ArrayList<>();
    int a = 1;

    public DadosLebre() {
        montarAnimais();
    }

    @Override
    public void montarAnimais(){
        lebres.add(new Lebre("Pulo Veloz"));
        lebres.add(new Lebre("Flash das Orelhas"));
        lebres.add(new Lebre("Saltitante Selvagem"));
        lebres.add(new Lebre("Turbo Coelho"));
    }

    @Override
    public void gerarVelocidade() {
        for (Lebre lebre : lebres) {
            int velocidade = random.nextInt(5) + 3;
            lebre.setVelocidade(velocidade);
        }

        for (Lebre lebre : lebres) {
            if (lebre.getVelocidade() == 3) {
                lebre.setOdd(10);
            } else if (lebre.getVelocidade() == 4) {
                lebre.setOdd(7);
            } else if (lebre.getVelocidade() == 5) {
                lebre.setOdd(4);
            } else if (lebre.getVelocidade() == 6) {
                lebre.setOdd(3);
            } else if (lebre.getVelocidade() == 7) {
                lebre.setOdd(2);
            }
        }
    }

    public List<Lebre> getLebres() {
        return lebres;
    }

    @Override
    public void mostrarAnimais() {
        gerarVelocidade();
        System.out.println();
        for (Lebre lebre : lebres) {
            System.out.println("Lebre " + a + ": " + lebre.getNome() + " Velocidade Maxima: " + lebre.getVelocidade() + " Multiplicação da aposta: " + lebre.getOdd());
            a++;
        }
    }
}
