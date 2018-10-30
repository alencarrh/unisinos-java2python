package unisinos.tradutores.java2python.translator;

public interface BaseTranslator {

    void setOutput(final StringBuilder output);

    StringBuilder getOutput();

    void translate();

}
