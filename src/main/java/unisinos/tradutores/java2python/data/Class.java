package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class Class {

    private final String name;
    private final boolean enumClass;
    @Singular
    final List<Element> elements;


}
