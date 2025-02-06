class Horista extends Empregado{
    double precoHora, horasTrabalhadas;

    Horista(String nome, String sobrenome, String cpf, double precoHora, double horasTrabalhadas){
        super(nome, sobrenome, cpf);
        this.precoHora = precoHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double vencimento(){
        return precoHora * horasTrabalhadas;
    }
}
