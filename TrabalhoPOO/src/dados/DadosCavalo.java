package dados;

import animais.Cavalo;

import java.util.ArrayList;
import java.util.List;

public class DadosCavalo extends Dados{
    private List<Cavalo> cavalos = new ArrayList<>();
    int a = 1;

    public DadosCavalo() {
        montarAnimais();
    }

    @Override
    public void montarAnimais(){
        cavalos.add(new Cavalo("Relâmpago Bravo"));
        cavalos.add(new Cavalo("Estrela da Serra"));
        cavalos.add(new Cavalo("Vento Selvagem"));
        cavalos.add(new Cavalo("Eclipse Dourado"));
        cavalos.add(new Cavalo("Trovada Veloz"));
        cavalos.add(new Cavalo("Falcão do Prado"));
        cavalos.add(new Cavalo("Tempestade Indomavel"));
    }

    @Override
    public void gerarVelocidade() {
        for (Cavalo cavalo : cavalos) {
            int velocidade = random.nextInt(6) + 6;
            cavalo.setVelocidade(velocidade);
        }

        for (Cavalo cavalo : cavalos) {
            if (cavalo.getVelocidade() == 6) {
                cavalo.setOdd(7);
            } else if (cavalo.getVelocidade() == 7) {
                cavalo.setOdd(6);
            } else if (cavalo.getVelocidade() == 8) {
                cavalo.setOdd(5);
            } else if (cavalo.getVelocidade() == 9) {
                cavalo.setOdd(4);
            } else if (cavalo.getVelocidade() == 10) {
                cavalo.setOdd(3);
            } else if (cavalo.getVelocidade() == 11){
                cavalo.setOdd(2);
            }
        }
    }

    public List<Cavalo> getCavalos() {
        return cavalos;
    }

    @Override
    public void mostrarAnimais() {
        gerarVelocidade();
        System.out.println();
        for (Cavalo cavalo : cavalos) {
            System.out.println("Cavalo " + a + ": "  + cavalo.getNome() + " Velocidade Maxima: " + cavalo.getVelocidade() + " Multiplicação da aposta: " + cavalo.getOdd());
            a++;
        }
    }

}
