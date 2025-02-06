class Comissionado extends Empregado {
    double taxaComissao, totalVenda;

    Comissionado(String nome, String sobrenome, String cpf, double totalVenda, double taxaComissao) {
        super(nome, sobrenome, cpf);
        this.totalVenda = totalVenda;
        this.taxaComissao = taxaComissao;
    }

    @Override
    public double vencimento(){
        return totalVenda * taxaComissao;
    }
}
