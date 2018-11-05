package unisinos.tradutores.java2python.translator;

import java.util.List;

import unisinos.tradutores.java2python.data.Class;

public interface Translator extends BaseTranslator {

    void setClasses(List<Class> classes);

}
