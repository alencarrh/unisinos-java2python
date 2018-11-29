package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import unisinos.tradutores.java2python.domain.VariableType;

@Data
@Builder
public class Method extends Element {

    /**
     * true - public false - private
     */
    private final boolean modifier;
    private final VariableType returnType;
    private final String name;
    @Singular
    private final List<Param> params;
    @Singular
    private final List<Expression> expressions;
    private final Integer scope = 1;

}
