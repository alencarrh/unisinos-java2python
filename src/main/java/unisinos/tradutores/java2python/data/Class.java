package unisinos.tradutores.java2python.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Class {

    private final String name;
    private final boolean enumClass;
    private final ClassBody body;

}
