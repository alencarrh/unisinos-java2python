package unisinos.tradutores.java2python.listener.builder;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

import java.util.Arrays;
import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.data.GenericBody;
import unisinos.tradutores.java2python.data.GenericStatement;
import unisinos.tradutores.java2python.data.Param;
import unisinos.tradutores.java2python.data.RepetitionStructure;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.BlockStatementsContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ExpressionContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForInitContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForUpdateContext;

@Builder
public class ForBuilder {

    private final ScopeLevel scope;

    public void build(Java8Parser.ForStatementContext ctx, Consumer<RepetitionStructure> callback) {

        final ForInitContext initContext = (ForInitContext) ctx.getChild(0).getChild(2);
        final Param forInitVariable = Param.builder()
            .type(VariableType.fromText(initContext.getChild(0).getChild(0).getText()))
            .name(initContext.getChild(0).getChild(1).getChild(0).getChild(0).getText())
            .initValue(initContext.getChild(0).getChild(1).getChild(0).getChild(2).getText())
            .build();

        final ExpressionContext expressionContext = (ExpressionContext) ctx.getChild(0).getChild(4);
        final Expression forCondition = Expression.builder()
            .expression(expressionContext.getText())
            .build();

        final ForUpdateContext updateContext = (ForUpdateContext) ctx.getChild(0).getChild(6);
        final Expression forUpdate = Expression.builder()
            .expression(updateContext.getText())
            .build();

        BlockStatementsContext statementContext = (BlockStatementsContext) ctx.getChild(0).getChild(8).getChild(0)
            .getChild(0).getChild(1);
        final GenericBody.GenericBodyBuilder forBody = GenericBody.builder();

        final StringBuilder finalExpression = new StringBuilder();
        BuilderUtils.forChildrenOf(statementContext, child -> {
            finalExpression.append(child.getText() + " ");
        });

        asList(finalExpression.toString().split("; ")).forEach(expression -> {
            GenericStatement genericExpression = null;
            final String[] expressionParts = expression.split(" ");

            if (expressionParts.length > 1 && expressionParts[1].equals("=")) {
                genericExpression = Expression.builder().expression(expression).build();
            } else if (expressionParts.length > 1 && expressionParts[2].equals("=")) {
                //é um Param
                genericExpression = Param.builder()
                    .type(VariableType.fromText(expressionParts[0]))
                    .name(expressionParts[1])
                    .initValue(Arrays.stream(expressionParts).skip(0).skip(1).skip(2).toArray(String[]::new))
                    .build();

            }

            if (nonNull(genericExpression)) {
                forBody.expression(genericExpression);
            }

        });

        final RepetitionStructure forRepetitionStructure = RepetitionStructure.builder()
            .initVariables(forInitVariable)
            .keepRunningCondition(forCondition)
            .updateVariables(forUpdate)
            .body(forBody.build())
            .scope(scope.currentLevel())
            .build();

        callback.accept(forRepetitionStructure);
    }

}
