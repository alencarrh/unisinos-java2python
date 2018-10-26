package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Data;

@Data
public class Constructor {

    private final String name;
    private final List<Param> params;

}
