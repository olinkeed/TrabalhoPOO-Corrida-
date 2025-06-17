package bancodedados;

import java.io.*;
import java.util.*;

/**
 * Classe que representa um sistema de banco de dados para gerenciar pontuações de jogadores.
 *
 * @author Carlos Alexandre
 * @author Carlos Eduardo
 * @author Rafael
 */
public class BancoDeDados {

    private static final String ARQUIVO = "pontuacoes.txt";

    /**
     * Salva ou atualiza a pontuação de um jogador.
     *
     * @param nome   O nome do jogador.
     * @param pontos A pontuação a ser salva ou atualizada.
     */
    public static void salvarOuAtualizar(String nome, double pontos) {
        Map<String, Double> jogadores = new HashMap<>();

        File file = new File(ARQUIVO);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(",");
                    if (partes.length == 2) {
                        jogadores.put(partes[0], Double.parseDouble(partes[1]));
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo de pontuação.");
            }
        }

        double novoSaldo = pontos;
        jogadores.put(nome, novoSaldo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Double> entry : jogadores.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pontuação.");
        }
    }

    /**
     * Busca a pontuação de um jogador pelo nome.
     *
     * @param nome O nome do jogador (key).
     * @return A pontuação do jogador, ou 0 se o jogador não for encontrado.
     */
    public static double buscarPontuacao(String nome) {
        File file = new File(ARQUIVO);
        if (!file.exists()) return 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2 && partes[0].equalsIgnoreCase(nome)) {
                    return Double.parseDouble(partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar pontuação.");
        }
        return 0;
    }

    /**
     * Exibe o ranking dos jogadores com suas respectivas pontuações.
     */
    public static void exibirRanking() {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            System.out.println("Nenhum jogador registrado ainda.");
            return;
        }

        List<String[]> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    dados.add(partes);
                }
            }

            dados.sort((a, b) -> Double.compare(Double.parseDouble(b[1]), Double.parseDouble(a[1])));

            System.out.println("===== RANKING DE JOGADORES =====");
            for (String[] jogador : dados) {
                System.out.println(jogador[0] + ": " + jogador[1] + " pontos");
            }

        } catch (IOException e) {
            System.out.println("Erro ao exibir ranking.");
        }
    }
}
