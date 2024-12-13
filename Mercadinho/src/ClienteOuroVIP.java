public class ClienteOuroVIP extends ClienteVIP {
    private String numeroCartao, endereco;
    private String tipoCliente = "(VIPOuro)";
    static double desconto = 0.15;

    public ClienteOuroVIP (String nome, double valorDaCompra, String numeroCartao, String endereco) {
        super(nome, valorDaCompra, numeroCartao);
        this.numeroCartao = numeroCartao;
        this.endereco = endereco;
    }

    public String getTipoCliente(){
        return " (VIP Ouro)";
    }

    public String getTipoCliente(String tipoCliente) {
        return tipoCliente;
    }

    @Override
    public double calcularPagamento(){
        return getValorDaCompra() * (1.00 - desconto);
    }

    @Override
    public String toString(){
        return super.toString() + ", Endereço: " + endereco;
    }

}
