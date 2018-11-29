package unisinos.tradutores.java2python.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class GenericBody extends Element {

    @Singular
    private final List<GenericStatement> expressions;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        expressions.forEach(e -> {
            sb.append(e.toString()).append("\n");
        });

        return sb.toString();
    }

    @Override
    public Element toggleScope() {
        return this;
    }
}
