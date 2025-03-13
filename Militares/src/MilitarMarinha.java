class MilitarMarinha extends Militar{
    boolean participouConsertoAltoMar;

    public MilitarMarinha(int matricula, Patente patente, boolean participouConsertoAltoMar){
        super(matricula, patente);
        this.participouConsertoAltoMar = participouConsertoAltoMar;
    }

    public boolean podeProgredir(){
        if (participouConsertoAltoMar & this.patente != Patente.Tenente){
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
