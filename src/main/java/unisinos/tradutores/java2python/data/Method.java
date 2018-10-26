package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Data;
import unisinos.tradutores.java2python.domain.VariableType;

@Data
public class Method {

    private final boolean modifier;
    private final VariableType returnType;
    private final String name;
    private final List<Param> params;

}
