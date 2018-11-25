package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Data;
import lombok.Singular;

@Data
public class Constructor {

    private final String name;
    @Singular
    private final List<Param> params;
    @Singular
    private final List<Expression> expressions;

}