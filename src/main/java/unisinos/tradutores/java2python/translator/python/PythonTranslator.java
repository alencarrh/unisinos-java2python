package unisinos.tradutores.java2python.translator.python;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.translator.Translator;

public class PythonTranslator implements Translator {

    private List<Class> classes;

    @Override
    public void setClasses(final List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public List<Class> getClasses() {
        return this.classes;
    }

    @Override
    public void translate() {

        //Aqui vai acontecer a tradução propriamente dita

    }
}
