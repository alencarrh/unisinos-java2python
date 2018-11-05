package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.translator.ClassBodyTranslator;
import unisinos.tradutores.java2python.translator.ClassTranslator;

public class PythonClassTranslator implements ClassTranslator {

    private List<Class> classes;
    private ClassBodyTranslator classBodyTranslator;
    private StringBuilder output;

    public PythonClassTranslator() {
        this.classBodyTranslator = new PythonClassBodyTranslator();
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
    public StringBuilder getOutput() {
        return this.output;
    }

    @Override
    public void translate() {
        this.classBodyTranslator.setOutput(output);

        this.classes.forEach(clazz -> {
            translateHeader(clazz);
            this.classBodyTranslator.setClassBody(clazz.getBody());
            this.classBodyTranslator.translate();
        });
    }

    private void translateHeader(final Class clazz) {
        //TODO faz a conversão do nome da classe, considerando se ela é um ENUM ou não..
        //TODO tem que adicionar informações de herança na Class e passsar para este método e tratar herança aqui
    }
}
