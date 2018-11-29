package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Expression extends GenericStatement {

    private final String expression;
    private final Integer scope;

}
