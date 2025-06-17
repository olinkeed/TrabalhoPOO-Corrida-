package informacoes;

import animais.Tartaruga;
import bancodedados.BancoDeDados;
import dados.DadosTartaruga;
import login.Login;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InformaçõesApostaTartaruga extends InformaçõesAposta{
    Scanner sc = new Scanner(System.in);

    private DadosTartaruga dados;
    private List<Tartaruga> tartarugas;
    private Login login;
    private int tartarugaVencedor;
    private boolean empate = false;

    public InformaçõesApostaTartaruga(Login login, DadosTartaruga dados) {
        super(0,0);
        this.login = login;
        this.dados = dados;
        tartarugas = dados.getTartarugas();
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public void setTartarugaVencedor(int tartarugaVencedor) {
        this.tartarugaVencedor = tartarugaVencedor;
    }

    @Override
    public int getAnimalEscolhido() {
        return animalEscolhido;
    }

    @Override
    public void setValorAposta(){
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("Qual será o valor da aposta?");
                valorAposta = sc.nextDouble();

                if (valorAposta <= 0.0) {
                    System.out.println("O valor da aposta não pode ser <= 0, digite novamente o valor da aposta:");
                    continue;
                }

                if (valorAposta > BancoDeDados.buscarPontuacao(login.getNome())) {
                    System.out.println("O valor da aposta é maior que seu saldo, digite novamente o valor da aposta:");
                    continue;
                }

                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Use números para definir o valor da aposta.");
                sc.nextLine();
            }
        }
        subtraiAposta();
    }

    @Override
    public void lerAnimal(){
        try {
            System.out.println();
            System.out.println("Escolha um tartaruga de 1 a 3 que deseja apostar:");
            animalEscolhido = sc.nextInt();
            while(animalEscolhido < 1 || animalEscolhido > 3){
                System.out.println("Escolha um tartaruga de 1 a 3 que deseja apostar:");
                animalEscolhido = sc.nextInt();
            }
        }catch (InputMismatchException e){
            sc.nextLine();
            lerAnimal();
        }
    }

    @Override
    public void subtraiAposta(){
        double pontuacao = BancoDeDados.buscarPontuacao(login.getNome()) - valorAposta;
        System.out.println("Pontuação: " + pontuacao);
        BancoDeDados.salvarOuAtualizar(login.getNome(), pontuacao);
    }

    @Override
    public void ganhouPerdeu() {
        if(empate){
            System.out.println("EMPATE! voce ganho 25% do premio total");
            BancoDeDados.salvarOuAtualizar(login.getNome(), BancoDeDados.buscarPontuacao(login.getNome()) + recebePremioEmpate());
        }else if(tartarugaVencedor == animalEscolhido) {
            System.out.println("Parabens, você venceu!!");
            BancoDeDados.salvarOuAtualizar(login.getNome(),BancoDeDados.buscarPontuacao(login.getNome()) + recebePremio());
        } else{
            System.out.println("Hoje não é seu dia de sorte, Voce perdeu!");
            System.out.println();
        }
    }

    @Override
    public double recebePremio() {
        for (int i = 0; i < 3; i++) {
            if  (animalEscolhido == (i  + 1)) {
                System.out.println("SEU PREMIO FOI DE: " + valorAposta * tartarugas.get(i).getOdd());
                System.out.println();
                return (valorAposta * tartarugas.get(i).getOdd());
            }
        }
        return 0;
    }

    @Override
    public double recebePremioEmpate(){
        for (int i = 0; i < 3; i++) {
            if (getAnimalEscolhido() == (i + 1)){
                System.out.println("SEU PREMIO FOI DE: " + (recebePremioEmpate() * 0.25));
                System.out.println();
                return (recebePremio() * 0.25);
            }
        }
        return 0;
    }
}
