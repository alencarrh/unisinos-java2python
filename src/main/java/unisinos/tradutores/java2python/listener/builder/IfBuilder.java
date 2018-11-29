package unisinos.tradutores.java2python.listener.builder;

import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.IfCondition;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.gramatica.Java8Parser;

@Builder
public class IfBuilder {

    private final ScopeLevel scope;

    public void build(final Java8Parser.IfThenStatementContext ctx, final Consumer<IfCondition> callback) {
        final IfCondition ifCondition = IfCondition.builder()
            .condition(ctx.getChild(2).getText())
            .scope(scope.currentLevel())
            .build();




//        System.out.println(ctx);
        callback.accept(ifCondition);

    }
}
