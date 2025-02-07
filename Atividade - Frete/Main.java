public class Main {
    public static void main(String[] args) {
        CadastroFrete cadastro = new CadastroFrete();

        FretePadrao frete1 = new FretePadrao(100, 2.5);
        FreteExpresso frete2 = new FreteExpresso(200, 3.0, 2);
        FreteSuperExpresso frete3 = new FreteSuperExpresso(150, 4.0, 3, 500);

        cadastro.addFrete(frete1);
        cadastro.addFrete(frete2);
        cadastro.addFrete(frete3);

        System.out.println("Valor total dos fretes: R$" + cadastro.calcularTotalFretes());

    }
}
// O polimorfismo acontece na lista de fretes (CadastroFrete), onde todos os fretes são armazenados como Fretavel, permitindo tratar diferentes tipos de frete de forma genérica.
// O método calcularFrete() é chamado de forma polimórfica, ou seja, cada objeto invoca a sua própria implementação, sem precisar verificar o tipo específico de frete.