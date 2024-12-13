abstract class Cliente {
    private String nome;
    private double valorDaCompra;

    public Cliente(String nome, double valorDaCompra) {
        this.nome = nome;
        this.valorDaCompra = valorDaCompra;
    }

    public String getNome() {
        return nome;
    }

    public abstract double calcularPagamento(); // Metodo abstrato para calcular o valor final da compra com ou sem desconto
    public abstract String getTipoCliente(); // Metodo para saber qual o tipo de cliente

    public double getValorDaCompra() {
        return this.valorDaCompra;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Valor da Compra: R$" + valorDaCompra + ", Valor da Compra com desconto: R$" + calcularPagamento();
    }

    public void add(Cliente cliente) {
    }
}