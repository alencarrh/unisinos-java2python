package unisinos.tradutores.java2python.exemplos;

public class ForExemplo {

    public void metodo1(int a, int b) {

        for (int i = a; i < b; i++) {
            System.out.println(i);
        }

    }

    public void metodo2() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i + j);
            }
        }

    }
}
