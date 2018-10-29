package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class ClassBody {

    @Singular
    private final List<Constructor> contructors;
    @Singular
    private final List<Method> methods;
    @Singular
    private final List<Variable> attributes;

}
