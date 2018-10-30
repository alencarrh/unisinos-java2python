package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Variable;
import unisinos.tradutores.java2python.translator.VariableTranslator;

public class PythonVariableTranslator implements VariableTranslator {

    private List<Variable> variables;
    private StringBuilder output;

    public PythonVariableTranslator() {
        this.output = new StringBuilder();
    }

    @Override
    public void setOutput(final StringBuilder output) {
        this.output = output;
    }

    @Override
    public StringBuilder getOutput() {
        return this.output;
    }

    @Override
    public void translate() {
    }

    @Override
    public void setVariables(final List<Variable> variables) {
        this.variables = variables;
    }
}
