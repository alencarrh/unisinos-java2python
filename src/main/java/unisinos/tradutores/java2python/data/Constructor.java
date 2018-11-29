package unisinos.tradutores.java2python.data;

import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class Constructor extends Element {

    private final String name;
    @Singular
    private final List<Param> params;
    @Singular
    private final List<Expression> expressions;
    private final Integer scope = 1;
    private boolean disableScope;


    @Override
    public Element toggleScope() {
        this.disableScope = !disableScope;
        return this;
    }


}
