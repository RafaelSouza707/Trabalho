import java.util.Scanner;
// teste
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
        player01.imprimirTabuleiro();

        System.out.println("Insira o segundo jogador.");
        System.out.println("Nome do jogador: ");
        String nomeJogador2 = scanner.nextLine();
        player02 = new Jogador(nomeJogador2);
        System.out.println("Jogador '"+ nomeJogador2 + "' inserido com sucesso.");
        player02.imprimirTabuleiro();

        do {

            int opcao = menuPlayer();

            switch (opcao){

                case 1:
                    menuArmas();
                    int opcaoArmas = scanner.nextInt();
                    player01.menuInserirArma(player01, opcaoArmas);
                    break;

                case 11:
                    player01.imprimirTabuleiro();
                    break;

                case 2:
                    menuArmas();
                    int opcaoArmas2 = scanner.nextInt();
                    player02.menuInserirArma(player02, opcaoArmas2);
                    break;

                case 22:
                    player02.imprimirTabuleiro();
                    break;

                case 3:
                    sair = 111;
                    break;
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
        System.out.println("[4] - Para finalizar a partida.");

        opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}
