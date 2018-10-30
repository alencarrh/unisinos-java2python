package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.translator.ClassTranslator;
import unisinos.tradutores.java2python.translator.Translator;

public class PythonTranslator implements Translator {

    private final ClassTranslator classTranslator;
    private List<Class> classes;
    private StringBuilder output;

    public PythonTranslator() {
        this.classTranslator = new PythonClassTranslator();
        this.output = new StringBuilder();
    }

    @Override
    public void setClasses(final List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public void setOutput(final StringBuilder output) {
        this.output = output;
    }

    @Override
    public List<Class> getClasses() {
        return this.classes;
    }

    @Override
    public void translate() {
        classTranslator.setClasses(classes);
        classTranslator.setOutput(output);
        classTranslator.translate();

        System.out.println(output.toString());
    }
}
