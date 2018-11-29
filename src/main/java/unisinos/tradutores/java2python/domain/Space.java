package unisinos.tradutores.java2python.domain;

import static org.apache.commons.lang3.BooleanUtils.isFalse;

public class Space {


    public static String getSpaces(int scope, boolean disable) {
        if(disable){
            return "";
        }
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < scope; i++) {
            sb.append("    ");
        }

        return sb.toString();
    }

}
