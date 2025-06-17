package dados;

import animais.Galinha;

import java.util.ArrayList;
import java.util.List;

public class DadosGalinha extends Dados{
    private List<Galinha> galinhas = new ArrayList<>();
    int a = 1;

    public DadosGalinha() {
        montarAnimais();
    }

    @Override
    public void montarAnimais(){
        galinhas.add(new Galinha("Asa Foguete"));
        galinhas.add(new Galinha("Penosa Veloz"));
        galinhas.add(new Galinha("Pé de Pato"));
        galinhas.add(new Galinha("Galinhatron"));
        galinhas.add(new Galinha("Fugidinha da Granja"));
        galinhas.add(new Galinha("Super Pernas"));
    }

    @Override
    public void gerarVelocidade() {
        for (Galinha galinha : galinhas) {
            int velocidade = random.nextInt(5) + 6;
            galinha.setVelocidade(velocidade);
        }

        for (Galinha galinha : galinhas) {
            if (galinha.getVelocidade() == 6) {
                galinha.setOdd(6);
            } else if (galinha.getVelocidade() == 7) {
                galinha.setOdd(5);
            } else if (galinha.getVelocidade() == 8) {
                galinha.setOdd(4);
            } else if (galinha.getVelocidade() == 9) {
                galinha.setOdd(3);
            } else if (galinha.getVelocidade() == 10) {
                galinha.setOdd(2);
            }
        }
    }

    public List<Galinha> getGalinhas() {
        return galinhas;
    }

    @Override
    public void mostrarAnimais() {
        gerarVelocidade();
        System.out.println();
        for (Galinha galinha : galinhas) {
            System.out.println("Galinha " + a + ": " + galinha.getNome() + " Velocidade Maxima: " + galinha.getVelocidade() + " Multiplicação da aposta: " + galinha.getOdd());
            a++;
        }
    }
}
