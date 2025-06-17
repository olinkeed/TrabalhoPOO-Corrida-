package informacoes;

import animais.Galinha;
import bancodedados.BancoDeDados;
import dados.DadosGalinha;
import login.Login;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InformaçõesApostaGalinha extends InformaçõesAposta{
    Scanner sc = new Scanner(System.in);

    private DadosGalinha dados;
    private List<Galinha> galinhas;
    private Login login;
    private int galinhaVencedor;
    private boolean empate = false;

    public InformaçõesApostaGalinha(Login login, DadosGalinha dados) {
        super(0,0);
        this.login = login;
        this.dados = dados;
        galinhas = dados.getGalinhas();
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public void setGalinhaVencedor(int galinhaVencedor) {
        this.galinhaVencedor = galinhaVencedor;
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
            System.out.println("Escolha um galinha de 1 a 6 que deseja apostar:");
            animalEscolhido = sc.nextInt();
            while(animalEscolhido < 1 || animalEscolhido > 6){
                System.out.println("Escolha um galinha de 1 a 6 que deseja apostar:");
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
        }else if(galinhaVencedor == animalEscolhido) {
            System.out.println("Parabens, você venceu!!");
            BancoDeDados.salvarOuAtualizar(login.getNome(),BancoDeDados.buscarPontuacao(login.getNome()) + recebePremio());
        } else{
            System.out.println("Hoje não é seu dia de sorte, Voce perdeu!");
            System.out.println();
        }
    }

    @Override
    public double recebePremio() {
        for (int i = 0; i < 6; i++) {
            if  (animalEscolhido == (i  + 1)) {
                System.out.println("SEU PREMIO FOI DE: " + valorAposta * galinhas.get(i).getOdd());
                System.out.println();
                return (valorAposta * galinhas.get(i).getOdd());
            }
        }
        return 0;
    }

    @Override
    public double recebePremioEmpate(){
        for (int i = 0; i < 6; i++) {
            if (getAnimalEscolhido() == (i + 1)){
                System.out.println("SEU PREMIO FOI DE: " + (recebePremio() * 0.25));
                System.out.println();
                return (recebePremio() * 0.25);
            }
        }
        return 0;
    }
}
