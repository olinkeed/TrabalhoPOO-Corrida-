package login;

import corrida.*;
import dados.*;
import informacoes.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que permite ao usuário escolher qual jogo deseja jogar.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class EscolhaJogo {
    Scanner sc = new Scanner(System.in);
    Corrida corrida = null;
    int num = 0;

    /**
     * Exibe as opções de jogos e solicita ao usuário que escolha um.
     *
     * @param login O objeto de login do usuário.
     * @return O objeto da corrida correspondente à escolha do usuário.
     */
    public Corrida escolheJogo(Login login) {
        System.out.println();
        System.out.println("Escolha qual jogo deseja jogar:");
        System.out.println("1 - Corrida de Cavalos \uD83D\uDC0E");
        System.out.println("2 - Corrida de Galinhas \uD83D\uDC14");
        System.out.println("3 - Corrida de Cachorro \uD83D\uDC15");
        System.out.println("4 - Corrida de Lebre \uD83D\uDC07");
        System.out.println("5 - Corrida de Tartaruga \uD83D\uDC22");

        return corrida = iniciaJogo(num, login);
    }

    /**
     * Inicia o jogo com base na escolha do usuário.
     *
     * @param num   O número da escolha do usuário.
     * @param login O objeto de login do usuário.
     * @return O objeto da corrida correspondente à escolha do usuário.
     */
    public Corrida iniciaJogo(int num, Login login) {
        Corrida corrida = null;

        while (true) {
            try {
                num = sc.nextInt();
                sc.nextLine();
                switch (num) {
                    case 1:
                        DadosCavalo dadosCavalo = new DadosCavalo();
                        InformaçõesApostaCavalo infoCavalo = new InformaçõesApostaCavalo(login, dadosCavalo);
                        return corrida = new CorridaCavalo(dadosCavalo, login, infoCavalo);

                    case 2:
                        DadosGalinha dadosGalinha = new DadosGalinha();
                        InformaçõesApostaGalinha infoGalinha = new InformaçõesApostaGalinha(login, dadosGalinha);
                        return corrida = new CorridaGalinha(dadosGalinha, login, infoGalinha);

                    case 3:
                        DadosCachorro dadosCachorro = new DadosCachorro();
                        InformaçõesApostaCachorro infoCachorro = new InformaçõesApostaCachorro(login, dadosCachorro);
                        return corrida = new CorridaCachorro(dadosCachorro, login, infoCachorro);

                    case 4:
                        DadosLebre dadosLebre = new DadosLebre();
                        InformaçõesApostaLebre infoLebre = new InformaçõesApostaLebre(login, dadosLebre);
                        return corrida = new CorridaLebre(dadosLebre, login, infoLebre);

                    case 5:
                        DadosTartaruga dadosTartaruga = new DadosTartaruga();
                        InformaçõesApostaTartaruga infoTartaruga = new InformaçõesApostaTartaruga(login, dadosTartaruga);
                        return corrida = new CorridaTartaruga(dadosTartaruga, login, infoTartaruga);

                    default:
                        System.out.println("Número inválido! Digite de 1 a 5:");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números de 1 a 5:");
                sc.nextLine();
            }
        }
    }
}
