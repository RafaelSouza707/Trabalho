import java.util.ArrayList;
import java.util.List;

public class Mercadinho {
    private List<Cliente> clientes;

    public Mercadinho() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Cliente cliente : clientes) {
            total += cliente.calcularPagamento();
        }
        return total;
    }

    public void imprimirClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}
