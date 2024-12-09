// Indices utilizados:
// (0) - para sucesso  (-1) - para lugar já ocupado (-2) - posicao invalida para essa arma. (-5) - limite de arma ja atingido.

public class Jogador {
    private String meuJogo, jogoDoAdversario;
    private char[][] tabuleiro = new char[8][8];
    private int subInseridos, cruInseridos, porInseridos;

    public Jogador(String meuJogo) {
        this.meuJogo = meuJogo;
        this.subInseridos = 0;
        this.cruInseridos = 0;
        this.porInseridos = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = 'o';  // Célula vazia representada por '0'
            }
        }
    }

    public String getNome() {
        return meuJogo;
    }

    public void atirar(Jogador jogoDoAdversario, int[] posicao) {
        String resultado = jogoDoAdversario.verificarSeAcertou(posicao);
        System.out.println(meuJogo + " atirou em: " + posicao[0] + "," + posicao[1]);
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

    public void imprimirTabuleiro(String nome) {
        System.out.println("Tabuleiro do Jogador " + meuJogo + ":");
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

    public int inserirSubmarino(int[] posicao){
        if(this.subInseridos <3){
            if(this.tabuleiro[posicao[0]][posicao[1]] == 'o'){
                this.tabuleiro[posicao[0]][posicao[1]] = 's';
                this.subInseridos ++;
                return 0; // Sucesso
            } else{
                System.out.println("Já existe uma arma nesta posição");
                return -1; // Já existe arma no local
            }
        } else{
            return -5; // Para limite de arma atingido.
        }
    }

    public int inserirCruzador(int[] posicao){
        if(this.cruInseridos < 2){
            if(posicao[1] != 7){
                if(this.tabuleiro[posicao[0]][posicao[1]] == 'o' & this.tabuleiro[posicao[0]][posicao[1]+1] == 'o'){
                    this.tabuleiro[posicao[0]][posicao[1]] = 'c';
                    this.tabuleiro[posicao[0]][posicao[1]+1] = 'c';
                    this.cruInseridos ++;
                    return 0; // Sucesso
                } else{
                    return -1; // Já existe arma no local
                }
            } else {
                return -2; // Posicao invalida para cruzador
            }
        } else{
            return -5; // Para limite de arma atingido.
        }
    }

    public int inserirPortaAviao(int[] posicao){
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
                    return 0; // Sucesso
                } else {
                    return -1; // Já existe arma no local
                }
            } else {
                return -2; // Posicao invalida para cruzador
            }
        } else{
            return -5; // Para limite de arma atingido.
        }
    }

    public String verificaAcao(int resultado){
        return switch (resultado) {
            case 0 -> "Arma alocada com sucesso!";
            case -1 -> "Já existe uma arma no local!";
            case -2 -> "Posiçao inválida! Não há espaço!";
            case -5 -> "Limite de armas atingido!";
            default -> "erro! tente novamente.";
        };
    }

    /*
    public String menuArmas(){
        System.out.println("Escolha qual arma deseja inserir: ");
        System.out.println("[1] - Submarinos");
        System.out.println("[2] - Cruzadores");
        System.out.println("[3] - Porta-avioes");
        return "";
    }

     */
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