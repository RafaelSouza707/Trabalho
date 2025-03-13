import java.util.ArrayList;
import java.util.List;

enum Patente{
    Soldado, Cabo, Tenente
}

abstract class Militar implements Carreira {
        int matricula;
        Patente patente;

        public Militar(int matricula, Patente patente){
            this.matricula = matricula;
            this.patente = patente;
        }

        abstract int getMatricula();
        abstract Patente getPatente();
}
