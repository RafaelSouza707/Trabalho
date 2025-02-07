import java.util.ArrayList;
import java.util.List;

public class CadastroFrete {
    private List<Fretavel> fretes = new ArrayList<>();

    public void addFrete(Fretavel frete){
        fretes.add(frete);
    }

    public double calcularTotalFretes() {
        double total = 0;
        for (Fretavel frete : fretes) {
            total += frete.calcularFrete();
        }
        return total;
    }
}
