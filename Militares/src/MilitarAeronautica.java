class MilitarAeronautica extends Militar{
    int tempoPatente, horasDeVoo;
    public MilitarAeronautica(int matricula, Patente patente, int tempoPatente, int horasDeVoo){
        super(matricula, patente);
        this.tempoPatente = tempoPatente;
        this.horasDeVoo = horasDeVoo;
    }

    public boolean podeProgredir(){
        if (tempoPatente >= 24 & horasDeVoo >= 200 & this.patente != Patente.Tenente){
            return true;
        } else {
            return false;
        }
    }

    public void progredir(){
        if (podeProgredir()) {
            switch (this.patente){
                case Patente.Soldado -> this.patente = Patente.Cabo;
                case Patente.Cabo -> this.patente = Patente.Tenente;
            }
        } else {
            System.out.println("Militar não pode ser promovido!");
        }
    }

    public int getMatricula(){
        return this.matricula;
    }
    public Patente getPatente(){
        return this.patente;
    }
}
