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

    private boolean disableScope;

    @Override
    public Element toggleScope() {
        this.disableScope = !disableScope;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("\n");
        if (nonNull(initVariables)) {
            sb.append(initVariables.toString()).append("\n");
        }
        sb.append(Space.getSpaces(scope, disableScope)).append("while (");
        sb.append(keepRunningCondition.toggleScope().toString()).append("):");
//        sb.append(Space.getSpaces(disableScope ? scope + 1 : 1, false)).append(body.toString());
        if (nonNull(updateVariables)) {
            sb.append("\n").append(Space.getSpaces(disableScope ? scope + 1 : 1, false))
                .append(updateVariables.toString());
        }

        return sb.toString();
    }
}
