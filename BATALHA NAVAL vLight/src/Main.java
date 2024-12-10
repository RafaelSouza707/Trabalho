import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogador player01 = null;
        Jogador player02 = null;
        int sair = 000;

        System.out.println("Insira o primeiro jogador.");
        System.out.println("Nome do jogador: ");
        String nomeJogador1 = scanner.nextLine();
        player01 = new Jogador(nomeJogador1);
        System.out.println("Jogador '"+ nomeJogador1 + "' inserido com sucesso.");
        player01.imprimirTabuleiro(player01.getTabuleiro());

        System.out.println("Insira o segundo jogador.");
        System.out.println("Nome do jogador: ");
        String nomeJogador2 = scanner.nextLine();
        player02 = new Jogador(nomeJogador2);
        System.out.println("Jogador '"+ nomeJogador2 + "' inserido com sucesso.");
        player02.imprimirTabuleiro(player02.getTabuleiro());

        do {

            int opcao = menuPlayer();

            switch (opcao) {

                case 1:
                    menuArmas();
                    int opcaoArmas = scanner.nextInt();
                    player01.menuInserirArma(player01, opcaoArmas);
                    break;

                case 11:
                    player01.imprimirTabuleiro(player01.getTabuleiro());
                    break;

                case 2:
                    menuArmas();
                    int opcaoArmas2 = scanner.nextInt();
                    player02.menuInserirArma(player02, opcaoArmas2);
                    break;

                case 22:
                    player02.imprimirTabuleiro(player02.getTabuleiro());
                    break;

                case 3:
                    do {
                        for(int i = 2; i > 0; i--) {
                            System.out.println("Jogador1: Digite a posicao onde deseja acertar o disparo. (Disparos restantes: " + i + ")");
                            int linha = scanner.nextInt();
                            int coluna = scanner.nextInt();
                            int[] escolhaPosicao = new int[]{linha, coluna};
                            player01.atirar(player02, escolhaPosicao);

                            if(player02.getVida() == 0){
                                System.out.println("O jogador: " + player01.getNome() + " venceu!");
                                break;
                            }
                        }

                        if (player02.getVida() > 0){
                            for(int i = 2; i > 0; i--) {
                                System.out.println("Jogador2: Digite a posicao onde deseja acertar o disparo. (Disparos restantes: " + i + ")");
                                int linha = scanner.nextInt();
                                int coluna = scanner.nextInt();
                                int[] escolhaPosicao = new int[]{linha, coluna};
                                player02.atirar(player01, escolhaPosicao);

                                if(player01.getVida() == 0){
                                    System.out.println("O jogador: " + player02.getNome() + " venceu!");
                                    break;
                                }
                            }
                        }

                    } while (player01.getVida() > 0 && player02.getVida() > 0);
                    break;

                case 4:
                    sair = 111;
                    break;
                default:
                    System.out.println("Tente novamente");

            }

        } while (sair != 111);

        scanner.close();
    }

    public static String menuArmas(){
        System.out.println("Escolha qual arma deseja inserir: ");
        System.out.println("[1] - Submarinos");
        System.out.println("[2] - Cruzadores");
        System.out.println("[3] - Porta-avioes");
        return "";
    }

    public static int menuPlayer(){
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("[1] - Para o jogador 1 Inserir armas no tabuleiro.");
        System.out.println("[11] - Para visualizar o tabuleiro do primeiro jogador.");
        System.out.println("[2] - Para o jogador 2 Inserir armas no tabuleiro.");
        System.out.println("[22] - Para visualizar o tabuleiro do primeiro jogador.");
        System.out.println("[3] - Para começar a partida.");
        System.out.println("[4] - Finalizar a partida.");

        opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}