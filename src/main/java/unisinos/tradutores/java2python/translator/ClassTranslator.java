package unisinos.tradutores.java2python.translator;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;

public interface ClassTranslator {

    void setClasses(final List<Class> classes);

    void setOutput(final StringBuilder output);

    void translate();

}
