package unisinos.tradutores.java2python.listener.builder;

import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.data.GenericBody;
import unisinos.tradutores.java2python.data.RepetitionStructure;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.gramatica.Java8Parser;

@Builder
public class WhileBuilder {

    private final ScopeLevel scope;

    public void build(final Java8Parser.WhileStatementContext ctx, final Consumer<RepetitionStructure> callback) {
        RepetitionStructure.RepetitionStructureBuilder whileBuilder = RepetitionStructure.builder();

        whileBuilder.keepRunningCondition(
            Expression.builder()
                .expression(ctx.getChild(2).getText())
                .scope(scope.currentLevel())
                .build()
        );

        GenericBody.GenericBodyBuilder bodyBuilder = GenericBody.builder();

        whileBuilder.body(bodyBuilder.build());

        callback.accept(whileBuilder.scope(scope.currentLevel()).build());


    }

}
