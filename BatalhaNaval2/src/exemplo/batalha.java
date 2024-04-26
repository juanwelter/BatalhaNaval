package exemplo;

import java.util.Scanner;

public class batalha {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        // Cria duas matrizes 10x10 para representar os tabuleiros dos jogadores
        char[][] tabuleiro = new char[10][10];
        char[][] tabuleiro2 = new char[10][10];

        // Cria uma instância da classe "naval" para gerenciar o jogo
        naval n1 = new naval();

        System.out.println("Seja Bem Vindo na batalha naval");
        System.out.println("=========== BATALHA NAVAL=========");

        // Preenche o tabuleiro com '~'
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = '~';
                tabuleiro2[i][j] = '~';
            }
        }

        int modoJogo = 0;
        boolean modoValido = false;
        while (!modoValido) {
            System.out.println("Escolha o modo de jogo:");
            System.out.println("1 - Jogador vs. Computador");
            System.out.println("2 - Jogador vs. Jogador");
            modoJogo = ler.nextInt();

            // Verifica se a opção escolhida é válida
            if (modoJogo == 1 || modoJogo == 2) {
                modoValido = true;
            } else {
                System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }

        // Seleciona a ação de acordo com o modo de jogo escolhido
        switch (modoJogo) {
            case 1:

                Scanner ler3 = new Scanner(System.in);

                boolean jogarNovamente = true;

                System.out.println("\nModo de jogo selecionado: Jogador vs Máquina");
                System.out.println();

                // Loop para permitir que o jogador selecione se deseja alocar os navios automaticamente ou manualmente
                while (jogarNovamente) {
                    System.out.println("Deseja alocar os navios automaticamente (1) ou manualmente (2)?");

                    String opcao3 = ler3.next();

                    switch (opcao3) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro);
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro);
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                System.out.println("\nTabuleiro do jogador 1");
                n1.exibirTabuleiro(tabuleiro);
                System.out.println("\nTabuleiro da máquina");
                n1.exibirTabuleiro(tabuleiro2);

                // Loop para realizar as jogadas até que um dos jogadores vença
                boolean jogos = true;
                while (jogos) {
                    System.out.println("\nTabuleiro da Máquina");
                    n1.exibirTabuleiroSemBarcos(tabuleiro2);
                    System.out.println("Sua vez de jogar jogador 1:");
                    n1.jogada(tabuleiro2, 'X');

                    // Verifica se o jogador acertou algum barco
                    if (n1.verificarVitoria(tabuleiro2)) {
                        System.out.println("Parabéns! Você afundou todos os navios do computador.");
                        break;
                    }

                    System.out.println("\nTabuleiro do jogador");
                    n1.exibirTabuleiroSemBarcos(tabuleiro);
                    System.out.println("\nVez do computador:");
                    n1.jogadaComputador(tabuleiro, 'O');

                    // Verifica se o computador acertou algum barco
                    if (n1.verificarVitoria(tabuleiro)) {
                        System.out.println("O computador afundou todos os seus navios. Fim de jogo.");
                        break;
                    }
                }
                break;

            case 2:

                System.out.println("\nModo de jogo selecionado: Jogador vs. Jogador");

                System.out.println("Digite o nome do primeiro jogador:");
                String jogador1 = ler.next();
                System.out.println("Digite o nome do segundo jogador:");
                String jogador2 = ler.next();
                Scanner ler1 = new Scanner(System.in);
                Scanner ler2 = new Scanner(System.in);

                // Define variáveis para controlar se os jogadores desejam alocar os navios automaticamente ou manualmente
                boolean jogarNovamente1 = true;
                boolean jogarNovamente2 = true;

                // Loop para permitir que o primeiro jogador selecione como deseja alocar os navios
                while (jogarNovamente1) {
                    System.out.println(jogador1 + " deseja alocar os navios automaticamente (1) ou manualmente (2)?");
                    String opcao1 = ler1.next();

                    switch (opcao1) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro);
                            jogarNovamente1 = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro);
                            jogarNovamente1 = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                // Exibe o tabuleiro do primeiro jogador após a alocação dos navios
                n1.exibirTabuleiro(tabuleiro);

                while (jogarNovamente2) {
                    System.out.println(jogador2 + " deseja alocar os navios automaticamente (1) ou manualmente (2)?");
                    String opcao1 = ler2.next();

                    // Realiza a ação de acordo com a opção escolhida pelo jogador
                    switch (opcao1) {
                        case "1":
                            n1.alocarNaviosAutomaticamente(tabuleiro2);
                            jogarNovamente2 = false;
                            break;
                        case "2":
                            n1.alocarNaviosManualmente(tabuleiro2);
                            jogarNovamente2 = false;
                            break;
                        default:
                            System.out.println("Opção inválida. Repetindo a jogada.");
                            break;
                    }
                }

                // Exibe o tabuleiro do segundo jogador após a alocação dos navios
                n1.exibirTabuleiro(tabuleiro2);

                // Loop para realizar as jogadas até que um dos jogadores vença
                boolean jogo = true;
                while (jogo) {
                    // Jogada do jogador 1
                    System.out.println("\nAtire no campo " + jogador2);
                    System.out.println("Tabuleiro - " + jogador2);
                    n1.exibirTabuleiroSemBarcos(tabuleiro2);
                    System.out.println("Sua vez de jogar: " + jogador1);
                    n1.jogada(tabuleiro2, 'X');

                    // Verifica se o jogador 1 acertou algum navio
                    if (n1.verificarVitoria(tabuleiro2)) {
                        System.out.println("Parabéns! " + jogador1 + " afundou todos os navios de " + jogador2);
                        jogo = false;
                        break;
                    }

                    // Jogada do jogador 2
                    System.out.println("\nAtire no campo " + jogador1);
                    System.out.println("Tabuleiro - " + jogador1);
                    n1.exibirTabuleiroSemBarcos(tabuleiro);
                    System.out.println("\nSua vez de jogar: " + jogador2);
                    n1.jogada(tabuleiro, 'O');

                    // Verifica se o jogador 2 acertou algum barco
                    if (n1.verificarVitoria(tabuleiro)) {
                        System.out.println("Parabéns! " + jogador2 + " afundou todos os navios de " + jogador1);
                        jogo = false;
                        break;
                    }
                }
        }
    }
}