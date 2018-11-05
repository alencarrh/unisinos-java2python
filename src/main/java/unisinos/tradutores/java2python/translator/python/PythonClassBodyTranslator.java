package unisinos.tradutores.java2python.translator.python;

import unisinos.tradutores.java2python.data.ClassBody;
import unisinos.tradutores.java2python.translator.ClassBodyTranslator;
import unisinos.tradutores.java2python.translator.MethodTranslator;

public class PythonClassBodyTranslator implements ClassBodyTranslator {

    private MethodTranslator methodTranslator;
    private PythonConstructorTranslator constructorTranslator;

    private ClassBody classBody;
    private StringBuilder output;

    public PythonClassBodyTranslator() {
        this.methodTranslator = new PythonMethodTranslator();
        this.constructorTranslator = new PythonConstructorTranslator();
        this.output = new StringBuilder();
    }

    @Override
    public void setClassBody(final ClassBody classBody) {
        this.classBody = classBody;
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
        setValues();
        this.constructorTranslator.translate();
        this.methodTranslator.translate();
    }

    private void setValues() {
        this.methodTranslator.setMethods(classBody.getMethods());
        this.constructorTranslator.setConstrutors(classBody.getContructors());
        this.constructorTranslator.setAttributes(classBody.getAttributes());

        this.methodTranslator.setOutput(output);
        this.constructorTranslator.setOutput(output);
    }
}
