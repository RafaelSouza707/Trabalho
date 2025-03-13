import java.util.ArrayList;
import java.util.List;

public class CadastroMilitar {
    List<Militar> militares = new ArrayList<>();

    public void addMilitar(Militar militar){
        militares.add(militar);
        System.out.println("Militar Adicionado!");
    }

    public void listarMilitar(){
        if(militares.isEmpty()){
            System.out.println("Nenhum militar cadastrado!");
            return;
        }
        System.out.println("Lista de Militares:");
        for(Militar militar : militares){
            if (militar.podeProgredir()) {
                System.out.println("O militar matricula: " + militar.getMatricula() + " Patente: " + militar.getPatente() + ";");
            }
        }
    }

    public void promoverMilitar(){
        for(Militar militar : militares){
            if (militar.podeProgredir()){
                militar.progredir();
                System.out.println("Militar promovido: " + militar.getMatricula());
            }
        }
    }
}
