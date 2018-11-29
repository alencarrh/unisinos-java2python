package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IfCondition extends Element {

    private final String condition;
    public final GenericBody body;
    private final Integer scope;
    public final IfCondition elseCondition;

}
