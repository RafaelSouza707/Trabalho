class ClienteRegular extends Cliente{
    public ClienteRegular(String nome, double valorDaCompra) {
        super(nome, valorDaCompra);
    }

    // Método para retornar o tipo do cliente
    public String getTipoCliente() {
        return " (Regular)";
    }

    @Override
    public double calcularPagamento() {
        return getValorDaCompra();
    }

    @Override
    public String toString() {
        return super.toString() + getTipoCliente();
    }
}