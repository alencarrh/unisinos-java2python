package unisinos.tradutores.java2python.data;

import static java.util.Objects.nonNull;

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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(Space.getSpaces(scope)).append("def ");
        if (!modifier) {
            sb.append("__");
        }
        sb.append(name).append("(");

        if (nonNull(params) && !params.isEmpty()) {
            sb.append(params.get(0).toString());

            params.forEach(param -> {
                sb.append(", ").append(param.toString());
            });
        }

        sb.append("):\n");

        expressions.forEach(e -> {
            sb.append(Space.getSpaces(scope + 1)).append(e.getExpression()).append("\n");
        });

        return sb.toString();
    }
}
