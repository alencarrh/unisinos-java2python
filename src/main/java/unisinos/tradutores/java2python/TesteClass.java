package unisinos.tradutores.java2python;

public class TesteClass {

    private String nome;
    private int quantidade;

    public void method1() {
        System.out.println("TESTE");
    }

    public String method2() {
        return "teste";
    }

    public void method3() {
        int a = 1;
        int b = 10;
        if (a == 1) {
            a = a * 2;
        } else {
            a = a + 1;
        }

        if (a == b) {
            b = b * 3;
            if (b == 10) {
                a = 2;
            }
        }
    }

    public void method4(String a, int b, int c, Double d, TipoTeste tipo) {
        if (a.equals("b")) {
            for (int i = 0; i < b; i++) {
                c = c + b;
                int x = 1;
                for (int l = 0; l < x; l++) {
                    b += 1;
                }
                x = c * -2;
            }
        }

        int i = c;

        while (i > 0) {
            b = b - c;
            i = i - b;
        }

    }
}

enum TipoTeste {
    TESTE1,
    TESTE2,
    TESTE3,
    TESTE4;
}
