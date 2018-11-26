package unisinos.tradutores.java2python.domain;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VariableType {

    // TODO map others
    OBJECT("any"),
    STRING("String"),
    INTEGER("int", "Integer"),
    BOOLEAN("boolean", "Boolean"),
    DOUBLE("double", "Double"),
    FLOAT("float", "Float"),
    VOID("void", "Void");

    VariableType(String... texts) {
        this.texts = asList(texts);
    }

    private final List<String> texts;

    public static VariableType fromText(final String fromText) {
        if (isBlank(fromText)) {
            return VariableType.OBJECT;
        }

        VariableType variables[] = VariableType.values();
        for (VariableType variableType : variables) {
            for (String text : variableType.getTexts()) {
                if (text.equals(fromText)) {
                    return variableType;
                }
            }
        }

        return VariableType.OBJECT;
    }

}
