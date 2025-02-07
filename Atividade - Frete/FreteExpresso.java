class FreteExpresso extends Frete {
    private int nivelUrgencia;

    public FreteExpresso(double distanciaKm, double valorKm, int nivelUrgencia) {
        super(distanciaKm, valorKm);
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public double calcularFrete(){
        return distanciaKm * valorKm  + (nivelUrgencia * 100);
    }
}