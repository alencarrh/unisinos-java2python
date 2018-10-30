package unisinos.tradutores.java2python.translator;

import java.util.List;

import unisinos.tradutores.java2python.data.Method;

public interface MethodTranslator extends BaseTranslator {

    void setMethods(final List<Method> methods);

}
