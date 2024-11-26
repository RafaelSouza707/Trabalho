public class Helicoptero {
    private boolean ativo;
    private int altitude, maxCapacidade, qntdPessoas;


    public Helicoptero(int maxCapacidade){
        this.ativo = false;
        this.altitude = 0;
        this.maxCapacidade = maxCapacidade;
        this.qntdPessoas = 0;
    }
    public void Entra() {
        if (qntdPessoas < maxCapacidade) {
            this.qntdPessoas ++;
            System.out.println("Uma pessoa foi adicionada no helicóptero!");
        } else {
            System.out.println("O helicóptero já está cheio!");
        }
    }
    public void Sai() {
        if (qntdPessoas > 0) {
             this.qntdPessoas --;
            System.out.println("Uma pessoa foi retirada do helicóptero!");
        } else {
            System.out.println("O helicóptero já está vazio!");
        }
    }
    public void Ligar() {
        if (!ativo) {
            this.ativo = true;
            System.out.println("O helicóptero foi ligado!");
        } else {
            System.out.println("O helicóptero já está ligado!");
        }
    }
    public void Desligar() {
        if (ativo) {
            this.ativo = false;
            System.out.println("O helicóptero foi desligado!");
        } else {
            System.out.println("O helicóptero já está desligado!");
        }
    }
    public void Decolar(int altitude) {
        if (ativo && this.altitude == 0) {
            this.altitude = altitude;
            System.out.println("O helicóptero decolou para a altitude " + altitude);
        } else {
            System.out.println("O helicóptero já está no ar!");
        }
    }
    public void Aterrissar() {
        if (ativo && this.altitude > 0) {
            this.altitude = 0;
            System.out.println("O helicóptero foi aterrissado!");
        } else {
            System.out.println("O helicóptero já está aterrissado!");
        }
    }

    // Encapsulamento.
    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getAltitude() {
        return altitude;
    }
    public void setAltitude(int altitude){
        this.altitude = altitude;
    }

    public int getMaxCapacidade() {
        return maxCapacidade;
    }
    public void setMaxCapacidade(int maxCapacidade){
        this.maxCapacidade = maxCapacidade;
    }

    public int getQntdPessoas() {
        return qntdPessoas;
    }
    public void setQntdPessoas(int qntdPessoas){
        this.qntdPessoas = qntdPessoas;
    }

    @Override
    public String toString() {
        return "Helicóptero {" +
                "Ativo: " + (ativo ? "Sim" : "Não") + ", " +
                "Altitude: " + altitude + ", " +
                "Capacidade Máxima: " + maxCapacidade + ", " +
                "Quantidade de pessoas dentro do Helicóptero: " + qntdPessoas +
                "}";
    }


}
