package unisinos.tradutores.java2python.data;

import static java.util.Objects.nonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import unisinos.tradutores.java2python.domain.Space;
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

    private boolean disableScope = false;


    @Override
    public Element toggleScope() {
        this.disableScope = !this.disableScope;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append(Space.getSpaces(scope, disableScope)).append("def ");
        if (!modifier) {
            sb.append("__");
        }
        sb.append(name).append("(self");

        if (nonNull(params) && !params.isEmpty()) {

            sb.append(",").append(params.get(0).toggleScope().toString());

            for (int i = 1; i < params.size(); i++) {
                sb.append(", ").append(params.get(i).toggleScope().toString());
            }
        }

        sb.append("): # returns ").append(returnType);

        expressions.forEach(e -> {
            sb.append(Space.getSpaces(scope + 1, disableScope)).append(e.getExpression().toString()).append("\n");
        });

        return sb.toString();
    }
}
