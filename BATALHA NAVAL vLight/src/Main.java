import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        Jogador player01 = null;
        Jogador player02 = null;

        do {

            System.out.println("[1] - Para criar jogador 1.");
            System.out.println("[2] - Para criar jogador 2.");
            System.out.println("[3] - Para o jogador 1 Inserir armas no tabuleiro.");
            System.out.println("[4] - Para o jogador 2 Inserir armas no tabuleiro.");
            System.out.println("[5] - Para começar a partida.");

            opcao = scanner.nextInt();
            scanner.nextLine();


            switch (opcao){

                case 1:
                    System.out.println("Nome do jogador: ");
                    String nomeJogador1 = scanner.nextLine();
                    player01 = new Jogador(nomeJogador1);
                    System.out.println("Jogador '"+ nomeJogador1 + "' inserido com sucesso.");

                case 2:
                    System.out.println("Nome do jogador: ");
                    String nomeJogador2 = scanner.nextLine();
                    player02 = new Jogador(nomeJogador2);
                    System.out.println("Jogador '"+ nomeJogador2 + "' inserido com sucesso.");

                case 3:
                    menuArmas();

                    int escolhaArmaPlayer1 = scanner.nextInt();

                    switch (escolhaArmaPlayer1){
                        case 1:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaSub = scanner.nextInt();
                            int colunaSub = scanner.nextInt();
                            int[] escolhaPosicaoSub = new int[]{linhaSub, colunaSub};
                            int resultadoPlayer1Sub = player01.inserirSubmarino(escolhaPosicaoSub);
                            System.out.println(player01.verificaAcao(resultadoPlayer1Sub));
                            break;
                        case 2:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaCru = scanner.nextInt();
                            int colunaCru = scanner.nextInt();
                            int[] escolhaPosicaoCru = new int[]{linhaCru, colunaCru};
                            int resultadoPlayer1Cru = player01.inserirCruzador(escolhaPosicaoCru);
                            System.out.println(player01.verificaAcao(resultadoPlayer1Cru));
                            break;
                        case 3:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaPor = scanner.nextInt();
                            int colunaPor = scanner.nextInt();
                            int[] escolhaPosicaoPor = new int[]{linhaPor, colunaPor};
                            int resultadoPlayer1Por = player01.inserirPortaAviao(escolhaPosicaoPor);
                            System.out.println(player01.verificaAcao(resultadoPlayer1Por));
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }

                case 4:
                    menuArmas();

                    int escolhaArmaPlayer2 = scanner.nextInt();

                    switch (escolhaArmaPlayer2){
                        case 1:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaSub = scanner.nextInt();
                            int colunaSub = scanner.nextInt();
                            int[] escolhaPosicaoSub = new int[]{linhaSub, colunaSub};
                            int resultadoPlayer2Sub = player02.inserirSubmarino(escolhaPosicaoSub);
                            System.out.println(player02.verificaAcao(resultadoPlayer2Sub));
                            break;
                        case 2:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaCru = scanner.nextInt();
                            int colunaCru = scanner.nextInt();
                            int[] escolhaPosicaoCru = new int[]{linhaCru, colunaCru};
                            int resultadoPlayer2Cru = player02.inserirCruzador(escolhaPosicaoCru);
                            System.out.println(player02.verificaAcao(resultadoPlayer2Cru));
                            break;
                        case 3:
                            System.out.println("Escolha a linha que deseja por a arma e logo após a coluna.");
                            int linhaPor = scanner.nextInt();
                            int colunaPor = scanner.nextInt();
                            int[] escolhaPosicaoPor = new int[]{linhaPor, colunaPor};
                            int resultadoPlayer2Por = player02.inserirPortaAviao(escolhaPosicaoPor);
                            System.out.println(player02.verificaAcao(resultadoPlayer2Por));
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 5:
                    break;
            }

        } while (opcao == 0);


        Jogador n1 = new Jogador("Salazar");
        n1.imprimirTabuleiro(n1.getNome());
        int resultado = n1.inserirSubmarino(new int[]{0, 1});
        int resultado1 = n1.inserirCruzador(new int[]{0, 6});
        int resultado2 = n1.inserirPortaAviao(new int[]{0, 2});
        switch (resultado1){
            case 0:
                System.out.println("Arma inserida com sucesso!");
                break;
            case -1:
                System.out.println("Já existe uma arma nesta posição. Tente novamente.");
                break;
            case -2:
                System.out.println("Vocë não pode alocar um Cruzador nesta posição, poís não há espaço.");
                break;
        }
        switch (resultado2){
            case 0:
                System.out.println("Arma inserida com sucesso!");
                break;
            case -1:
                System.out.println("Já existe uma arma nesta posição. Tente novamente.");
                break;
            case -2:
                System.out.println("Vocë não pode alocar um Cruzador nesta posição, poís não há espaço.");
                break;
        }

        n1.imprimirTabuleiro(n1.getNome());
    }

    public static String menuArmas(){
        System.out.println("Escolha qual arma deseja inserir: ");
        System.out.println("[1] - Submarinos");
        System.out.println("[2] - Cruzadores");
        System.out.println("[3] - Porta-avioes");
        return "";
    }
}