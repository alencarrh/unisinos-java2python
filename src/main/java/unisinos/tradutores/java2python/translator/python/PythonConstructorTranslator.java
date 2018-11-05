package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Constructor;
import unisinos.tradutores.java2python.data.Variable;
import unisinos.tradutores.java2python.translator.ConstructorTranslator;

public class PythonConstructorTranslator implements ConstructorTranslator {

    private List<Constructor> constructors;
    private List<Variable> attributes;
    private StringBuilder output;

    @Override
    public void setConstrutors(final List<Constructor> construtors) {
        this.constructors = construtors;
    }

    public void setAttributes(final List<Variable> attributes) {
        this.attributes = attributes;
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
        //TODO precisa considerar se há mais de um construtor
        // e também todos os atributos devem ser inicializados dentro do contrutor com o valor "None"
        //TODO 2: tratar valores default no construtor
    }
}
