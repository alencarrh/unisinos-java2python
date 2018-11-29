package unisinos.tradutores.java2python.data;

import static java.util.Objects.nonNull;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.Space;

@Data
@Builder
public class RepetitionStructure extends Element {

    private final Param initVariables;
    private final Expression keepRunningCondition;
    private final Expression updateVariables;
    private final GenericBody body;
    private final Integer scope;

    public String toString() {
        final StringBuilder body = new StringBuilder();
        if (nonNull(this.body)) {
            this.body.getExpressions().forEach(a -> {
                body.append(Space.getSpaces(scope)).append(a).append("\n");
            });
        }

        return this.initVariables.getName() + " = " + this.initVariables.getInitValue() + "\n" +
            "while" + this.getKeepRunningCondition() +
            body +
            getUpdateVariables().getExpression();
    }
}
