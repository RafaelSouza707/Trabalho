import java.util.HashSet;
class FolhaDePagamento {
    private HashSet<Empregado> empregados;

    public FolhaDePagamento() {
        empregados = new HashSet<>();
    }

    public void adicionarFuncionario(Empregado empregado) {
        if (!empregados.contains(empregado)) {
            empregados.add(empregado);
            System.out.println("Funcionário " + empregado.nome + " foi adicionado com sucesso.");
        } else {
            System.out.println("Funcionário " + empregado.nome + " já existe na folha de pagamento.");
        }
    }

    public void exibirTodos() {
        for (Empregado empregado : empregados) {
            System.out.println("Nome: " + empregado.nome + ", Vencimento: R$" + empregado.vencimento());
        }
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Empregado empregado : empregados) {
            total += empregado.vencimento();
        }
        return total;
    }
}