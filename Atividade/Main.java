/*
Example: ["1001,D,300", "1002,C,200","1001,D,300"]
Output : Balance -> -400
Output : Balance -> -100
*/

public class Main {
    public static void main(String[] args) {
        String[] arr = { "1001,D,300", "1002,C,200", "1001,D,300" };

        System.out.println(balance(arr));
    }

    public static int balance(String[] balances) {
        int totalBalance = 0;

        for (String transacao : balances) {
            String[] parts = transacao.split(",");
            String type = parts[1];
            int amount = Integer.parseInt(parts[2]);

            if (type.equals("D")) {
                totalBalance -= amount;
            } else if (type.equals("C")) {
                totalBalance += amount;
            }
        }

        return totalBalance;
    }

}