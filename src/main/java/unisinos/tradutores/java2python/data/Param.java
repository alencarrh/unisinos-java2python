package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.VariableType;

@Data
@Builder
public class Param extends GenericStatement {

    private final String name;
    private final VariableType type;
    private final Object initValue;
    private final Integer scope;

}
