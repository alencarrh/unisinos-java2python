package unisinos.tradutores.java2python;

public class ClasseExemplo5 {

    public String param1;
    public Integer param2;
    public String param3;

    public ClasseExemplo5(String param1, Integer param2, String param3) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    public void executeFor(String[] param5) {
        for (int i = 0; i < param5.length; i++) {
            System.out.println(param5[i]);
        }
    }

    public void executeWhile(String[] param5) {
        int index = 0;
        while (index < param5.length) {
            System.out.println(param5[index]);
            index++;
        }
    }

    @Override
    public String toString() {
        return param1;
    }
}

