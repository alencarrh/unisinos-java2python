package unisinos.tradutores.java2python;

public class TesteClass {

    private String nome;
    private int quantidade;

    public void method1() {
        int a = 1;
        int b = 10;

        for (int i = 50; i < 0; i += 1) {
            a = a + b;
        }
    }

    public String method2(int a, int b) {
        System.out.println("teste123");

        while (a < b) {
            a += 1;
        }

        if (b == a) {
            return "teste";
        }

        return "asdasd";
    }

    public void method3(int a, int b) {

        if (a == 1) {
            a = a * 2;
        }

        if (a == b) {
            b = b * 3;
            if (b == 10) {
                a = 2;
                int c = a + b;
                if (c == 15) {
                    c *= 2;
                }
                for (int i = c; i < 0; i--) {
                    a = a + b;
                }
            }
        }
    }


    public void method4(String a, int b, int c, Double d, TipoTeste tipo) {
        int g = c;
        if (c == b) {
            for (int i = 0; i < b; i++) {
                c = c + b;
                int x = 1;
                x = c * 2;
                for (int l = 0; l < x; l++) {
                    b += 1;
                }
            }
        }

        while (g > 0) {
            b = b - c;
            g = g - b;
        }

    }
}

enum TipoTeste {
    TESTE1,
    TESTE2,
    TESTE3,
    TESTE4;
}
