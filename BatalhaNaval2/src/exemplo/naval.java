package exemplo;

import java.util.Scanner;
import java.util.Random;
public class naval {
    public static void alocarNavio(char[][] tabuleiro, int tamanho, Random aleatorio, char letra) {
        // Método para alocar um navio de um determinado tamanho em uma posição aleatória no tabuleiro
        int coluna, linha;
        // Gera uma posição aleatória para o navio
        do {
            coluna = aleatorio.nextInt(10);
            linha = aleatorio.nextInt(10);
        } while (!posicaoValida(tabuleiro, linha, coluna, tamanho));

        // Coloca o navio no tabuleiro na posição gerada
        for (int i = 0; i < tamanho; i++) {
            tabuleiro[linha][coluna + i] = letra;
        }
    }

    public static boolean posicaoValida(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        // Verifica se a posição para alocar o navio é válida no tabuleiro
        if (coluna + tamanho > 10) {
            return false;
        }

        // Verifica se não há nenhum navio já alocado na posição
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[linha][coluna + i] != '~') {
                return false;
            }
        }
        return true;
    }

    public static void alocarNaviosAutomaticamente(char[][] tabuleiro) {
        // Método para alocar os navios automaticamente no tabuleiro
        Random random = new Random();

        // Alocar navios de tamanhos diferentes
        alocarNavio(tabuleiro, 4, random, 'w');
        for (int i = 0; i < 2; i++) {
            alocarNavio(tabuleiro, 3, random, 'x');
        }
        for (int i = 0; i < 3; i++) {
            alocarNavio(tabuleiro, 2, random, 'y');
        }
        for (int i = 0; i < 4; i++) {
            alocarNavio(tabuleiro, 1, random, 'z');
        }
    }

    public static void alocarNavio1(char[][] tabuleiro, int tamanho, Scanner ler, char letra) {
        // Método para alocar um navio manualmente no tabuleiro
        int linha, coluna;
        char orientacao;

        do {
            // Solicita ao jogador as informações sobre a posição e orientação do navio
            System.out.println("Digite a coluna do navio (A-J):");
            String colunas = ler.next().toUpperCase();
            System.out.println("Digite a linha do navio (0-9):");
            linha = ler.nextInt();
            System.out.println("Digite a orientação do navio (H - horizontal, V - vertical):");
            orientacao = ler.next().toUpperCase().charAt(0);
            String alfabeto = "ABCDEFGHIJ";
            coluna = alfabeto.indexOf(colunas);

            // Verifica se a posição e orientação escolhidas pelo jogador são válidas
            if (orientacao == 'H' && posicaoValidaHorizontal(tabuleiro, linha, coluna, tamanho)) {
                // Coloca o navio no tabuleiro na posição escolhida
                for (int j = 0; j < tamanho; j++) {
                    tabuleiro[linha][coluna + j] = letra;
                }
                break;
            } else if (orientacao == 'V' && posicaoValidaVertical(tabuleiro, linha, coluna, tamanho)) {
                // Coloca o navio no tabuleiro na posição escolhida
                for (int i = 0; i < tamanho; i++) {
                    tabuleiro[linha + i][coluna] = letra;
                }
                break;
            } else {
                System.out.println("Jogada inválida. Tente novamente.");
            }
        } while (true);

        // Exibe o tabuleiro após a alocação do navio
        exibirTabuleiro(tabuleiro);
    }

    public static void alocarNaviosManualmente(char[][] tabuleiro) {
        // Método para alocar os navios manualmente no tabuleiro
        Scanner ler = new Scanner(System.in);

        // Alocar navios de tamanhos diferentes
        alocarNavio1(tabuleiro, 4, ler, 'w');
        for (int i = 0; i < 2; i++) {
            alocarNavio1(tabuleiro, 3, ler, 'x');
        }
        for (int i = 0; i < 3; i++) {
            alocarNavio1(tabuleiro, 2, ler, 'y');
        }
        for (int i = 0; i < 4; i++) {
            alocarNavio1(tabuleiro, 1, ler, 'z');
        }
    }

    public static boolean posicaoValidaVertical(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        // Verifica se a posição vertical para alocar o navio é válida no tabuleiro
        if (linha + tamanho > 10) {
            return false;
        }

        // Verifica se não há nenhum navio já alocado na posição
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[linha + i][coluna] != '~') {
                return false;
            }
        }
        return true;
    }

    public static boolean posicaoValidaHorizontal(char[][] tabuleiro, int linha, int coluna, int tamanho) {
        // Verifica se a posição horizontal para alocar o navio é válida no tabuleiro
        if (coluna + tamanho > 10) {
            return false;
        }
        for (int j = 0; j < tamanho; j++) {
            if (tabuleiro[linha][coluna + j] != '~') {
                return false;
            }
        }
        return true;
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        // Método para exibir o tabuleiro
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void exibirTabuleiroSemBarcos(char[][] tabuleiro) {
        // Método para exibir o tabuleiro sem mostrar a posição dos navios
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == 'w' || tabuleiro[i][j] == 'x' || tabuleiro[i][j] == 'y' || tabuleiro[i][j] == 'z') {
                    System.out.print("~ ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void jogada(char[][] tabuleiro, char simbolo) {
        // Método para realizar uma jogada de um jogador
        Scanner ler = new Scanner(System.in);
        boolean acertouNavio = false;

        while (true) {
            // Solicita ao jogador a posição do tiro
            System.out.println("Digite a coluna do tiro (A-J):");
            String colunas = ler.next().toUpperCase();
            System.out.println("Digite a linha do tiro (0-9):");
            int linha = ler.nextInt();
            String alfabeto = "ABCDEFGHIJ";
            int coluna = alfabeto.indexOf(colunas);

            // Verifica se a posição escolhida pelo jogador é válida
            if (coluna < 0 || coluna >= 10 || linha < 0 || linha >= 10) {
                System.out.println("Posição inválida. Tente novamente.");
                break;
            }

            // Realiza a jogada e verifica o resultado
            if (tabuleiro[linha][coluna] == '~') {
                System.out.println("Água!");
                tabuleiro[linha][coluna] = '.';
                break;
            } else if (tabuleiro[linha][coluna] == 'w' || tabuleiro[linha][coluna] == 'x' || tabuleiro[linha][coluna] == 'y' || tabuleiro[linha][coluna] == 'z') {
                System.out.println("Acertou um navio!");
                tabuleiro[linha][coluna] = '-';
                acertouNavio = true;
                break;
            } else {
                System.out.println("Posição inválida. Tente novamente.");
            }
        }

        // Se acertou um navio e o jogo não acabou, realiza outra jogada
        if (acertouNavio && !verificarVitoria(tabuleiro)) {
            jogada(tabuleiro, simbolo);
        } else if (verificarVitoria(tabuleiro)) {
            System.out.println("========PARABENS==========");
        }
    }

    public static void jogadaComputador(char[][] tabuleiro, char simbolo) {
        // Método para realizar uma jogada do computador
        Random posicao = new Random();
        boolean acertouNavio = false;

        while (true) { // Loop infinito até que o computador faça uma jogada válida
            int coluna = posicao.nextInt(10);
            int linha = posicao.nextInt(10);
            String alfabeto = "ABCDEFGHIJ";
            char letraColuna = alfabeto.charAt(coluna);
            int numeroLinha = linha;

            System.out.println("O computador jogou em: " + letraColuna + numeroLinha);

            // Realiza a jogada e verifica o resultado
            if (tabuleiro[linha][coluna] == '~') {
                System.out.println("O computador errou o tiro!");
                tabuleiro[linha][coluna] = '.';
                break;
            } else if (tabuleiro[linha][coluna] == 'w' || tabuleiro[linha][coluna] == 'x' || tabuleiro[linha][coluna] == 'y' || tabuleiro[linha][coluna] == 'z') {
                System.out.println("O computador acertou um navio!");
                tabuleiro[linha][coluna] = '-';
                acertouNavio = true;
                break;
            }
        }

        // Se acertou um navio e o jogo não acabou, realiza outra jogada
        if (acertouNavio && !verificarVitoria(tabuleiro)) {
            jogadaComputador(tabuleiro, simbolo);
        } else if (verificarVitoria(tabuleiro)) {
            System.out.println("========PARABENS==========");
        }
    }

    public static boolean verificarVitoria(char[][] tabuleiro) {
        // Verifica se todos os navios foram afundados
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == 'w' || tabuleiro[i][j] == 'x' || tabuleiro[i][j] == 'y' || tabuleiro[i][j] == 'z') {
                    if (tabuleiro[i][j] != '-') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
