package unisinos.tradutores.java2python.translator;

import java.util.List;

import unisinos.tradutores.java2python.data.Variable;

public interface VariableTranslator extends BaseTranslator {

    void setVariables(final List<Variable> variables);

}
