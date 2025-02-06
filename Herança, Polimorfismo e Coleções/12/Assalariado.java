class Assalariado extends Empregado {
    double salario;

    Assalariado(String nome, String sobrenome, String cpf, double salario) {
        super(nome, sobrenome, cpf);
        this.salario = salario;
    }
    @Override
    public double vencimento(){
        return salario;
    }
}
