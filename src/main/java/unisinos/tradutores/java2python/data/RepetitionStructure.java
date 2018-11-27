package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepetitionStructure {

    private final Param initVariables;
    private final Expression keepRunningCondition;
    private final Expression updateVariables;
    private final GenericBody body;


}
