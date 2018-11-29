package unisinos.tradutores.java2python.listener.builder;

import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Class;
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
        final Class clazz = Class.builder()
            .enumClass(true)
            .name(ctx.children.get(1).getText())
            .build();

        callback.accept(clazz);
    }

}
