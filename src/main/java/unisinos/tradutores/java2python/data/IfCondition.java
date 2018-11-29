package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.Space;

@Data
@Builder
public class IfCondition extends Element {

    private final String condition;
    public final GenericBody body;
    private Integer scope;
    public final IfCondition elseCondition;

    private boolean disableScope;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        String spaces = Space.getSpaces(scope, disableScope);
        sb.append(spaces).append("if (").append(condition).append("):");
//        sb.append(spaces).append(Space.getSpaces(1, false)).append(body.toString());

        return sb.toString();
    }

    @Override
    public Element toggleScope() {
        this.disableScope = !disableScope;
        return this;
    }
}
