import java.util.Random;

public class Guerreiros_da_POO {
    private int codigo, energia ;
    private String nome;
    private Random gerador;

    public Guerreiros_da_POO(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.energia = 5;
        this.gerador = new Random();
    }

    public int getEnergia() {
        return energia;
    }

    public void incremento() {
        if(this.energia < 5) {
            this.energia ++;
        }
    }
    public void decremento() {
        this.energia --;
    }

    public void alimentar(){
        incremento();
    }

    public int atacar() {
        return gerador.nextInt(2);
    }

    public void lutaLoop(Guerreiros_da_POO oponente) {
        while(this.getEnergia() > 0 && oponente.getEnergia() > 0) {
            if(this.atacar() == 1) {
                oponente.decremento();
                System.out.println("O Guerreiro " +oponente.nome + " perdeu 1 energia!");
            } else {
                System.out.println("O seu ataque não funcionou!");
            }
            if(oponente.getEnergia() > 0 && oponente.atacar() == 1) {
                this.decremento();
                System.out.println("O Guerreiro " + this.nome + " perdeu 1 energia");
            } else {
                System.out.println("O ataque do oponente não funcionou!");
            }
        }

        if (this.getEnergia() > 0) {
            System.out.println(this.nome + " é o vencedor!");
        } else {
            System.out.println(oponente.nome + " é o vencedor!");
        }
    }

    public void lutaRecursiva(Guerreiros_da_POO oponente) {
        if(this.getEnergia() <= 0 || oponente.getEnergia() <= 0) {
            if(this.getEnergia() > 0) {
                System.out.println(this.nome + " é o vencedor!");
            } else {
                System.out.println(oponente.nome + " é o vencedor!");
            }
            return;
        }

        if(this.atacar() == 1) {
            oponente.decremento();
            System.out.println("O Guerreiro " +oponente.nome + " perdeu 1 energia!");
        } else {
            System.out.println("O seu ataque não funcionou!");
        }

        if(oponente.getEnergia() > 0 && oponente.atacar() == 1) {
            this.decremento();
            System.out.println("O Guerreiro " + this.nome + " perdeu 1 energia");
        } else {
            System.out.println("O ataque do oponente não funcionou!");
        }

        this.lutaRecursiva(oponente);
    }

    public void reiniciar() {
        this.energia = 5;
    }
    @Override
    public String toString() {
        return "Guerreiro{" +
                "Código=" + codigo +
                ", Nome='" + nome + '\'' +
                ", Energia=" + energia +
                '}';
    }
}
