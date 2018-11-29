package unisinos.tradutores.java2python.data;

import static java.util.Objects.nonNull;

import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.Space;

@Data
@Builder
public class Expression extends GenericStatement {

    private final String expression;
    private final Integer scope;

    private boolean disableScope;



    @Override
    public Expression toggleScope(){
        this.disableScope = !disableScope;
        return this;
    }

    @Override
    public String toString() {
        if(nonNull(expression)) {
            return Space.getSpaces(scope, disableScope) + expression;
        }
        return "";
    }
}
