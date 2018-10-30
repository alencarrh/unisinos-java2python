package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.translator.ClassTranslator;
import unisinos.tradutores.java2python.translator.MethodTranslator;

public class PythonClassTranslator implements ClassTranslator {

    private MethodTranslator translator;
    private List<Class> classes;
    private StringBuilder output;

    @Override
    public void setClasses(final List<Class> classes) {
        this.classes = classes;
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
