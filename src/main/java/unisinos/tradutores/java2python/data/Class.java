package unisinos.tradutores.java2python.data;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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

    public void addAtr(String name){
        if (isNull(this.atr)) {
            this.atr = new ArrayList<>();
        }
        this.atr.add("    " + name + " = None");
    }

    public String toString(){
        final StringBuilder result = new StringBuilder();
        result.append("class " + name + ":\n");
        if (nonNull(this.atr)) {
            atr.forEach(a -> {
                result.append(a + "\n");
            });
        }

      return result.toString();
    }

}
