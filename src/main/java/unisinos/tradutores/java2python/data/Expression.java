package unisinos.tradutores.java2python.data;

import static java.util.Objects.nonNull;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.Space;

@Data
@Builder
public class Expression extends GenericStatement {

    private final String expression;
    private final Integer scope;


    @Override
    public String toString() {
        if(nonNull(expression)) {
            return Space.getSpaces(scope) + expression;
        }
        return "";
    }
}
