import java.util.ArrayList;
import java.util.List;

public class CadastroDeDesenhos {
    private List<Desenho> desenhos;

    public CadastroDeDesenhos(){
        desenhos = new ArrayList<>();
    }


    public void adicionarDesenho(Desenho desenho){
        desenhos.add(desenho);
    }
    public void desenharTodos(){
        for (Desenho desenho : desenhos){
            desenho.desenhar();
        }
    }
}