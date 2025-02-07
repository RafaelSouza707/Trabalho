abstract class Frete implements Fretavel {
    double distanciaKm, valorKm;

    public Frete(double distanciaKm, double valorKm){
        this.distanciaKm = distanciaKm;
        this.valorKm = valorKm;
    }
}
