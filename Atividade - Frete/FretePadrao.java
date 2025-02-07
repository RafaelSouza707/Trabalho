class FretePadrao extends Frete {
    public FretePadrao(double distanciaKm, double valorKm) {
        super(distanciaKm, valorKm);
    }

    @Override
    public double calcularFrete() {
        return distanciaKm * valorKm;
    }
}