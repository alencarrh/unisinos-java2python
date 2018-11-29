package unisinos.tradutores.java2python.exemplos;

public class WhileExemplo {

    public void metodo1(int a, int b) {

        while (a < b) {
            System.out.println(b);

            a++;
        }

    }

    public void metodo2() {
        int i = 0;
        while (i < 10) {
            int j = 0;
            while (j < 10) {
                System.out.println(i+j);
            }
        }
    }

}
