package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Method;
import unisinos.tradutores.java2python.translator.MethodTranslator;

public class PythonMethodTranslator implements MethodTranslator {

    private List<Method> methods;
    private StringBuilder output;

    public PythonMethodTranslator() {
        this.output = new StringBuilder();
    }

    @Override
    public void setMethods(final List<Method> methods) {
        this.methods = methods;
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
}
