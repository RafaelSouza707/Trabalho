public class Main {
    public static void main(String[] args) {
        Guerreiros_da_POO g1 = new Guerreiros_da_POO(01, "Guerreiro 01");
        Guerreiros_da_POO g2 = new Guerreiros_da_POO(02, "Guerreiro 02");

        System.out.println("\n=======================================");
        System.out.println("Ataque em loop.\n");
        g1.lutaLoop(g2);

        g1.reiniciar();
        g2.reiniciar();

        System.out.println("\n=======================================");
        System.out.println("Ataque recursivo. \n");
        g2.lutaRecursiva(g1);
        g1.alimentar();

        System.out.println(g1.toString());
    }
}