public class FreteSuperExpresso extends FreteExpresso{
    private double valorDoSeguro;

    public FreteSuperExpresso(double distanciaKm, double valorKm, int nivelUrgencia, double valorDoSeguro){
        super(distanciaKm, valorKm, nivelUrgencia);
        this.valorDoSeguro = valorDoSeguro;
    }

    @Override
    public double calcularFrete(){
        return super.calcularFrete() + valorDoSeguro;
    }
}
