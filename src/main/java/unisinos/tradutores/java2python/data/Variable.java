package unisinos.tradutores.java2python.data;

import lombok.Data;
import unisinos.tradutores.java2python.domain.Modifier;
import unisinos.tradutores.java2python.domain.VariableType;

@Data
public class Variable {

    private final Modifier modifier;
    private final VariableType type;
    private final String name;
}
