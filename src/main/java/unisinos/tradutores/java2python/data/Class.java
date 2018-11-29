package unisinos.tradutores.java2python.data;

import static java.util.Objects.isNull;

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

    public void addElement(final Element element) {
        if (isNull(this.elements)) {
            this.elements = new ArrayList<>();
        }
        this.elements.add(element);
    }

}
