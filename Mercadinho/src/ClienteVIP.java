class ClienteVIP extends Cliente{
    private String numeroCartao;
    static double desconto = 0.10;

    public ClienteVIP (String nome, double valorDaCompra, String numeroCartao){
        super(nome, valorDaCompra);
        this.numeroCartao = numeroCartao;
    }

    public String getTipoCliente(){
        return " (VIP)";
    }

    @Override
    public double calcularPagamento(){
        return getValorDaCompra() * (1.00 - desconto);
    }

    @Override
    public String toString(){
        return super.toString() + ", número do cartão: " + numeroCartao + getTipoCliente();
    }
}
