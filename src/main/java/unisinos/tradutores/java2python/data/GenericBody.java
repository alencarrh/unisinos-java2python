package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class GenericBody {

    @Singular
    private final List<GenericStatement> expressoins;

}
