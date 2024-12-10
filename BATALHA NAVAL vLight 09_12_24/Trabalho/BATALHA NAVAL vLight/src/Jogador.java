import java.util.Scanner;

public class Jogador {
    private String meuJogo, jogoDoAdversario;
    private char[][] tabuleiro = new char[8][8];
    private int subInseridos = 0, cruInseridos = 0, porInseridos = 0;
    Scanner scanner = new Scanner(System.in);

    public Jogador(String meuJogo) {
        this.meuJogo = meuJogo;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = 'o';  // Célula vazia representada por '0'
            }
        }
    }

    public int getSubInseridos(){
        return subInseridos;
    }
    public void incrementaSub(int valor){
        this.subInseridos = valor;
    }

    public int getCruInseridos(){
        return cruInseridos;
    }
    public void incrementaCru(int valor){
        this.cruInseridos = valor;
    }

    public int getPorInseridos(){
        return porInseridos;
    }
    public void incrementaPor(int valor){
        this.porInseridos = valor;
    }

    public String getNome() {
        return meuJogo;
    }

    public void atirar(Jogador jogoDoAdversario, int[] posicao) {
        String resultado = jogoDoAdversario.verificarSeAcertou(posicao);
        System.out.println(getNome() + " atirou em: " + posicao[0] + "," + posicao[1]);
        System.out.println("Resultado do tiro: " + resultado);
    }

    public String verificarSeAcertou(int[] posicao) {
        String resultado = "";
        if (tabuleiro[posicao[0]][posicao[1]] == 's' ||  // Submarino
                tabuleiro[posicao[0]][posicao[1]] == 'c' ||  // Cruzador
                tabuleiro[posicao[0]][posicao[1]] == 'p') {  // Porta-aviões
            tabuleiro[posicao[0]][posicao[1]] = 'X';  // Marcando como "X" quando acerta
            resultado = "Acertou!";
        } else {
            tabuleiro[posicao[0]][posicao[1]] = 'O';  // Marcando como "O" quando erra
            resultado = "Errou!";
        }
        return resultado;
    }

    public void imprimirTabuleiro() {
        System.out.println("Tabuleiro do Jogador " + this.meuJogo + ":");
        System.out.print("  ");
        for (int i = 0; i <= 7; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (char celula : tabuleiro[i]) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
    }

    public String inserirSubmarino(int[] posicao){
        if(getSubInseridos() < 3){
            if(this.tabuleiro[posicao[0]][posicao[1]] == 'o'){
                this.tabuleiro[posicao[0]][posicao[1]] = 's';
                incrementaSub(+1);
                System.out.println("Arma alocada com sucesso!");
                imprimirTabuleiro();
            } else{
                System.out.println("Já existe uma arma nesta posição");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
        return "";
    }

    public String inserirCruzador(int[] posicao){
        if(getCruInseridos() < 2){
            if(posicao[1] != 7){
                if(this.tabuleiro[posicao[0]][posicao[1]] == 'o' & this.tabuleiro[posicao[0]][posicao[1]+1] == 'o'){
                    this.tabuleiro[posicao[0]][posicao[1]] = 'c';
                    this.tabuleiro[posicao[0]][posicao[1]+1] = 'c';
                    incrementaCru(+1);
                    System.out.println("Arma alocada com sucesso!");
                    imprimirTabuleiro();
                } else{
                    System.out.println("Já existe uma arma no local!");
                }
            } else {
                System.out.println("Posiçao inválida! Não há espaço!");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
        return "";
    }

    public String inserirPortaAviao(int[] posicao){
        if(getPorInseridos() < 1){
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
                    incrementaCru(+1);
                    System.out.println("Arma alocada com sucesso!");
                    imprimirTabuleiro();
                } else {
                    System.out.println("Já existe uma arma no local!");
                }
            } else {
                System.out.println("Posiçao inválida! Não há espaço!");
            }
        } else{
            System.out.println("Limite de armas atingido!");
        }
        return "";
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

}

/*
    0 1 2 3 4 5 6 7
0   o o o o o o o o
1   o o o o o o o o
2   o o o o o o o o
3   o o o o o o o o
4   o o o o o o o o
5   o o o o o o o o
6   o o o o o o o o
7   o o o o o o o o

 */
