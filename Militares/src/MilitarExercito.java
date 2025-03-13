class MilitarExercito extends Militar{
    boolean participouGuerra, ganhouGuerra;
    public MilitarExercito(int matricula, Patente patente, boolean participouGuerra, boolean ganhouGuerra){
        super(matricula, patente);
        this.participouGuerra = participouGuerra;
        this.ganhouGuerra = ganhouGuerra;
    }

    public boolean podeProgredir(){
        if (participouGuerra & ganhouGuerra & this.patente != Patente.Tenente){
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
