package unisinos.tradutores.java2python.data;

import lombok.Data;
import unisinos.tradutores.java2python.domain.VariableType;

@Data
public class Param {

    private final String name;
    private final VariableType type;
    private final Object initValue;

}
