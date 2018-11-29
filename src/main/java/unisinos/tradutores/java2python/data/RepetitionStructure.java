package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;

import static java.util.Objects.nonNull;

@Data
@Builder
public class RepetitionStructure extends Element {

    private final Param initVariables;
    private final Expression keepRunningCondition;
    private final Expression updateVariables;
    private final GenericBody body;
    private final Integer scope;

    public String toString(){
        final StringBuilder body = new StringBuilder();
        if (nonNull(this.body)) {
            this.body.getExpressoins().forEach(a -> {
                body.append("    " + a + "\n");
            });
        }


        return this.initVariables.getName() + " = " + this.initVariables.getInitValue() + "\n" +
                "while" + this.getKeepRunningCondition() +
                body +
                getUpdateVariables().getExpression();
    }
}
