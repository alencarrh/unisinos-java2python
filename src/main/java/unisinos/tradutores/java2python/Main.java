package unisinos.tradutores.java2python;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.gramatica.Java8Lexer;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.listener.JavaBaseListenerImpl;

public class Main {


    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("ERRO ::::: Arquivo a ser analisado é obrigatório");
            return;
        }

        String inputFile = args[0];

        CharStream cs = CharStreams.fromPath(Paths.get(inputFile));

        Java8Lexer lexer = new Java8Lexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit(); // parse

        ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
        JavaBaseListenerImpl extractor = new JavaBaseListenerImpl();
        walker.walk(extractor, tree); // initiate walk of tree with listener

        List<Class> classes = extractor.build();

//        System.out.println("\n\n:::::: RESULTADO\n\n");

        final StringBuilder sb = new StringBuilder();

        classes.forEach(clazz -> sb.append(clazz.toString()));

        System.out.println(sb.toString());
        try (PrintStream out = new PrintStream(new FileOutputStream(inputFile + ".py"))) {
            out.println(sb.toString());
        }

    }
}
