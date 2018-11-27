package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class Class {

    private final String name;
    private final boolean enumClass;
    private final ClassBody body;

}
