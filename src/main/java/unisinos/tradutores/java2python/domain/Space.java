package unisinos.tradutores.java2python.domain;

public class Space {


    public static String getSpaces(int scope) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < scope; i++) {
            sb.append("    ");
        }

        return sb.toString();
    }

}
