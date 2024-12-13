public class Main {
    public static void main(String[] args) {
        // Criando o mercadinho
        Mercadinho mercadinho = new Mercadinho();

        // Adicionando clientes
        mercadinho.adicionarCliente(new ClienteRegular("João", 200.0));
        mercadinho.adicionarCliente(new ClienteVIP("Maria", 300.0, "1234-5678"));
        mercadinho.adicionarCliente(new ClienteOuroVIP("Carlos", 500.0, "9876-5432", "Rua das Flores, 123"));

        // Mostrando todos os clientes
        System.out.println("Clientes cadastrados no mercadinho:");
        mercadinho.imprimirClientes();

        // Mostrando o valor total das compras
        System.out.println("Valor total das compras: R$" + mercadinho.calcularTotal());
    }
}