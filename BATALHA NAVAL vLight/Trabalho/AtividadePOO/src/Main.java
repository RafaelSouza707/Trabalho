public class Main {
    public static void main(String[] args) {
        // Criar dois objetos da classe Helicóptero.
        Helicoptero heli01 = new Helicoptero(3);
        Helicoptero heli02 = new Helicoptero(4);

        // Exibir o estado dos helicópteros.
        System.out.println(heli01);
        System.out.println(heli02);

        // Realizar a entrada de pessoas nos helicópteros.
        heli01.Entra();
        heli01.Entra();
        heli01.Entra();

        heli02.Entra();
        heli02.Entra();
        heli02.Entra();
        heli02.Entra();

        // Ligar os helicópteros.
        heli01.Ligar();
        heli02.Ligar();

        // Decolar cada um dos helicópteros para uma altitude informada.
        heli01.Decolar(245);
        heli02.Decolar(321);

        // Exibir o estado dos helicópteros.
        System.out.println(heli01);
        System.out.println(heli02);

        // Aterrissar cada um dos helicópteros.
        heli01.Aterrissar();
        heli02.Aterrissar();

        // Desligar os helicópteros.
        heli01.Desligar();
        heli02.Desligar();

        // Realizar a saída de pessoas dos helicópteros.
        heli01.Sai();
        heli01.Sai();
        heli01.Sai();

        heli02.Sai();
        heli02.Sai();
        heli02.Sai();
        heli02.Sai();

        // Exibir os estados dos helicópteros.
        System.out.println(heli01);
        System.out.println(heli02);
    }
}