package unisinos.tradutores.java2python.translator;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;

public interface ClassTranslator extends BaseTranslator {

    void setClasses(final List<Class> classes);

}
