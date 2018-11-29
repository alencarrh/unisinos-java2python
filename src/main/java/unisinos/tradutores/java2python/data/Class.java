package unisinos.tradutores.java2python.data;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static unisinos.tradutores.java2python.domain.Space.getSpaces;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Class {

    private final String name;
    private final boolean enumClass;
    private List<Element> elements;
    private List<String> atr;

    public void addElement(final Element element) {
        if (isNull(this.elements)) {
            this.elements = new ArrayList<>();
        }
        this.elements.add(element);
    }

    public void addAtr(String name) {
        if (isNull(this.atr)) {
            this.atr = new ArrayList<>();
        }
        this.atr.add(getSpaces(1, false) + name + " = None");
    }

    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("class ").append(name).append(":\n");
        if (nonNull(this.atr)) {
            atr.forEach(a -> {
                result.append(a).append("\n");
            });
        }
        if (nonNull(elements)) {
            this.elements.forEach(e -> {
                result.append(e.toString()).append("\n");
            });
        }

        return result.toString()
            .replace("null", "None")
            .replace("--", "-= 1")
            .replace("++", "+= 1")
            .replace("System.out.println(", "print(");
    }

}
