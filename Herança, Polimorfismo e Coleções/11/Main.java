public class Main {
    public static void main(String[] args){
        CadastroDeDesenhos cadastro = new CadastroDeDesenhos();

        Desenho retangulo = new Retangulo();
        Desenho circulo = new Circulo();
        Desenho quadrado = new Quadrado();
        Desenho pessoa = new Pessoa();

        cadastro.adicionarDesenho(retangulo);
        cadastro.adicionarDesenho(circulo);
        cadastro.adicionarDesenho(quadrado);
        cadastro.adicionarDesenho(pessoa);

        cadastro.desenharTodos();
    }
}

/*
    O polimorfismo acontece no uso das variáveis na classe principal, pois, apesar de serem do mesmo tipo (interface Desenho),
    suas implementações dos métodos são diferentes e o comportamento é definido em tempo de execução.

    Além disso, ele também ocorre no método desenharTodos(), onde o método desenhar() é executado de forma genérica para cada objeto.
    O comportamento específico de cada chamada é definido em tempo de execução, de acordo com a implementação da classe concreta do objeto referenciado.
 */