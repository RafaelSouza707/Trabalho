public class Main {
    public static void main(String[] args){
        FolhaDePagamento folha = new FolhaDePagamento();

        Empregado empregado1 = new Assalariado("João", "Silva", "123456789", 3000.0);
        Empregado empregado2 = new Comissionado("Maria", "Oliveira", "987654321", 500.0, 5);
        Empregado empregado3 = new Assalariado("Carlos", "Pereira", "456789123", 3500.0);
        Empregado empregado4 = new Horista("Ana", "Costa", "321654987", 42.0, 75);

        folha.adicionarFuncionario(empregado1);
        folha.adicionarFuncionario(empregado2);
        folha.adicionarFuncionario(empregado3);
        folha.adicionarFuncionario(empregado4);

        System.out.println("\nExibindo todos os funcionários:");
        folha.exibirTodos();

        double valorTotal = folha.calcularValorTotal();
        System.out.println("\nValor total da folha de pagamento: R$" + valorTotal);
    }
}
