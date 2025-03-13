public class Main {
    public static void main(String[] args) {
        CadastroMilitar cadastro = new CadastroMilitar();

        // Militar Aeronautica
        cadastro.addMilitar(new MilitarAeronautica(101, Patente.Soldado, 25, 230));
        cadastro.addMilitar(new MilitarAeronautica(102, Patente.Cabo, 12, 300));
        cadastro.addMilitar(new MilitarAeronautica(103, Patente.Tenente, 32, 350));
        // Militar Exercito
        cadastro.addMilitar(new MilitarExercito(201, Patente.Soldado, true, true));
        cadastro.addMilitar(new MilitarExercito(202, Patente.Cabo, true, false));
        cadastro.addMilitar(new MilitarExercito(203, Patente.Tenente, true, true));
        // Militar Marinha
        cadastro.addMilitar(new MilitarMarinha(301, Patente.Soldado, true));
        cadastro.addMilitar(new MilitarMarinha(302, Patente.Cabo, true));
        cadastro.addMilitar(new MilitarMarinha(303, Patente.Tenente, true));

        System.out.println();
        cadastro.listarMilitar();

        System.out.println();
        System.out.println("Militares em promoção: ");
        cadastro.promoverMilitar();
    }
}