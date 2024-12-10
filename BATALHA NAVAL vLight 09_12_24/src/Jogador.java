import java.util.Scanner;

public class Jogador {
    private String meuJogo;
    private char[][] jogoDoAdversario = new char[8][8];
    private char[][] tabuleiro = new char[8][8];
    private int subInseridos = 0, cruInseridos = 0, porInseridos = 0, vida = 12;
    Scanner scanner = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";    // Reseta a cor
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARELO = "\u001B[33m";
    public static final String ROXO = "\u001B[35m";
    public static final String BRANCO = "\u001B[37m";

    public Jogador(String meuJogo) {
        this.meuJogo = meuJogo;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = 'o';  // Célula vazia representada pela letra 'o'
                jogoDoAdversario[i][j] = 'o';
            }
        }
    }

    public String getNome() {
        return AZUL + meuJogo + RESET;
    }

    public int getVida() {
        return this.vida;
    }

    public char[][] getTabuleiro(){
        return this.tabuleiro;
    }

    public void atirar(Jogador adversario, int[] posicao) {
        String resultado = adversario.verificarSeAcertou(posicao);
        System.out.println(getNome() + " atirou em: " + posicao[0] + "," + posicao[1]);
        System.out.println("Resultado do tiro: " + resultado);
        adversario.imprimirTabuleiro(adversario.jogoDoAdversario);
    }

    public String verificarSeAcertou(int[] posicao) {
        String resultado = "";
        if (tabuleiro[posicao[0]][posicao[1]] == 's' ||  // Submarino
                tabuleiro[posicao[0]][posicao[1]] == 'c' ||  // Cruzador
                tabuleiro[posicao[0]][posicao[1]] == 'p') {  // Porta-aviões
            this.vida --;
            tabuleiro[posicao[0]][posicao[1]] = 'X'; // Marcando como "X" quando acerta
            jogoDoAdversario[posicao[0]][posicao[1]] = 'X';
            resultado = "Acertou!";

        } else {
            tabuleiro[posicao[0]][posicao[1]] = 'i';  // Marcando como "i" quando erra
            jogoDoAdversario[posicao[0]][posicao[1]] = 'i';
            resultado = "Errou!";

        }
        return resultado;
    }

    public void imprimirTabuleiro(char[][] tTabuleiro) {
        System.out.println("Tabuleiro do Jogador " + this.meuJogo + ":");
        System.out.print("  ");
        for (int i = 0; i <= 7; i++) {
            System.out.print(AZUL + i + " " + RESET);
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(AZUL + i + " " + RESET);
            for (char celula : tTabuleiro[i]) {
                String cor = escolherCorPorValor(celula);
                System.out.print(cor + celula + " " + RESET);
            }
            System.out.println();
        }
    }

    public void inserirSubmarino(int[] posicao){
        if(this.subInseridos < 3){
            if(this.tabuleiro[posicao[0]][posicao[1]] == 'o'){
                this.tabuleiro[posicao[0]][posicao[1]] = 's';
                this.subInseridos ++;
                System.out.println("Arma alocada com sucesso!");
                imprimirTabuleiro(this.tabuleiro);
            } else{
                System.out.println("Já existe uma arma nesta posição");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
    }

    public void inserirCruzador(int[] posicao){
        if(this.cruInseridos < 2){
            if(posicao[1] != 7){
                if(this.tabuleiro[posicao[0]][posicao[1]] == 'o' & this.tabuleiro[posicao[0]][posicao[1]+1] == 'o'){
                    this.tabuleiro[posicao[0]][posicao[1]] = 'c';
                    this.tabuleiro[posicao[0]][posicao[1]+1] = 'c';
                    this.cruInseridos ++;
                    System.out.println("Arma alocada com sucesso!");
                    imprimirTabuleiro(this.tabuleiro);
                } else{
                    System.out.println("Já existe uma arma no local!");
                }
            } else {
                System.out.println("Posiçao inválida! Não há espaço!");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
    }

    public void inserirPortaAviao(int[] posicao){
        if(this.porInseridos < 1){
            if (posicao[1] < 4) {
                if (this.tabuleiro[posicao[0]][posicao[1]] == 'o' &
                        this.tabuleiro[posicao[0]][posicao[1]+1] == 'o' &
                        this.tabuleiro[posicao[0]][posicao[1]+2] == 'o' &
                        this.tabuleiro[posicao[0]][posicao[1]+3] == 'o' &
                        this.tabuleiro[posicao[0]][posicao[1]+4] == 'o') {
                    this.tabuleiro[posicao[0]][posicao[1]] = 'p';
                    this.tabuleiro[posicao[0]][posicao[1] + 1] = 'p';
                    this.tabuleiro[posicao[0]][posicao[1] + 2] = 'p';
                    this.tabuleiro[posicao[0]][posicao[1] + 3] = 'p';
                    this.tabuleiro[posicao[0]][posicao[1] + 4] = 'p';
                    this.porInseridos ++;
                    System.out.println("Arma alocada com sucesso!");
                    imprimirTabuleiro(this.tabuleiro);
                } else {
                    System.out.println("Já existe uma arma no local!");
                }
            } else {
                System.out.println("Posiçao inválida! Não há espaço!");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
    }


    public void menuInserirArma(Jogador player, int arma){
        System.out.println("Escolha a linha inicial que deseja por a arma e logo após a coluna.");
        int linha = scanner.nextInt();
        int coluna = scanner.nextInt();
        int[] escolhaPosicao = new int[]{linha, coluna};
        switch (arma){
            case 1:
                player.inserirSubmarino(escolhaPosicao);
                break;

            case 2:
                player.inserirCruzador(escolhaPosicao);
                break;

            case 3:
                player.inserirPortaAviao(escolhaPosicao);
                break;
        }
    }

    public static String escolherCorPorValor(char valor) {
        if (valor == 's') return VERDE;
        if (valor == 'c') return AMARELO;
        if (valor == 'p') return ROXO;
        if (valor == 'X') return VERMELHO;
        if (valor == 'i') return BRANCO;
        return RESET;
    }
}