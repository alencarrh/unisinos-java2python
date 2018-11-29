package unisinos.tradutores.java2python.listener.builder;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.data.Element;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumBodyContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;

@Builder
public class ClassBuilder {

    public void build(final NormalClassDeclarationContext ctx, final Consumer<Class> callback) {
        final Class clazz = Class.builder()
            .enumClass(false)
            .name(ctx.children.get(2).getText())
            .build();

        callback.accept(clazz);
    }

    public void build(final EnumDeclarationContext ctx, final Consumer<Class> callback) {
        String[] values = ((EnumBodyContext) ctx.children.get(2)).children.get(1).getText().split(",");

        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            elements.add(Expression.builder()
                .scope(1)
                .expression(values[i] + " = " + i)
                .build());
        }

        final Class clazz = Class.builder()
            .enumClass(true)
            .name(ctx.children.get(1).getText())
            .elements(elements)
            .build();

        callback.accept(clazz);
    }

}
