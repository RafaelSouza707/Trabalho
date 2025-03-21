import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] arr = { "1001,D,300", "1002,C,200", "1001,D,300" };

        System.out.println("Saldo final: " + balance(arr));
    }

    public static int balance(String[] balances) {
        int saldo = 0;
        List<String> transacoes = new ArrayList<>();

        for (String transacao : balances) {
            if (!transacoes.contains(transacao)) {
                transacoes.add(transacao);
                System.out.println("Inserido: " + transacao);

                String[] parts = transacao.split(",");
                String type = parts[1];
                int amount = Integer.parseInt(parts[2]);

                if (type.equals("D")) {
                    saldo -= amount;
                } else if (type.equals("C")) {
                    saldo += amount;
                }

            } else {
                System.out.println("Transação duplicada ignorada: " + transacao);
            }
        }

        System.out.println("Lista final de transações: " + transacoes);

        return saldo;
    }
}
