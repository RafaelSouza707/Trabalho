import java.util.*;

public class BaseballGame {
    public static int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (String op : ops) {
            if (op.equals("c")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (op.equals("d")) {
                if (!stack.isEmpty()) stack.push(stack.peek() * 2);
            } else if (op.equals("+")) {
                if (stack.size() >= 2) {
                    int top = stack.pop();
                    int newTop = top + stack.peek();
                    stack.push(top);
                    stack.push(newTop);
                }
            } else {
                stack.push(Integer.parseInt(op));
            }
        }

        return stack.stream().mapToInt(Integer::intValue).sum(); // Soma todos os valores da pilha
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite as operações separadas por espaço");

        String input = sc.nextLine(); // Lê a entrada como uma única linha
        String[] ops = input.split(" "); // Divide a string em um array

        System.out.println("Resultado: " + calPoints(ops));

        sc.close();
    }
}
