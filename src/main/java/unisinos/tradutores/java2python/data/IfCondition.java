package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;
import unisinos.tradutores.java2python.domain.Space;

@Data
@Builder
public class IfCondition extends Element {

    private final String condition;
    public final GenericBody body;
    private final Integer scope;
    public final IfCondition elseCondition;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String spaces = Space.getSpaces(scope);
        sb.append(spaces).append("if ( ").append(condition).append("):\n");
        sb.append(spaces).append(Space.getSpaces(1)).append(body.toString());

        return sb.toString();
    }
}
