package unisinos.tradutores.java2python.listener.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.antlr.v4.runtime.tree.ParseTree;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.data.GenericStatement;
import unisinos.tradutores.java2python.data.Param;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.LocalVariableDeclarationStatementContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.StatementWithoutTrailingSubstatementContext;

@Builder
public class BlockBuilder {

    private final ScopeLevel scope;

    public void build(final Java8Parser.BlockStatementsContext ctx, final Consumer<List<GenericStatement>> callback) {
        final List<GenericStatement> expressions = new ArrayList<>();

        ctx.children.forEach(child -> {
            ParseTree childChild = child.getChild(0);
            if (childChild instanceof LocalVariableDeclarationStatementContext) {
                VariableType type = VariableType.fromText(childChild.getChild(0).getChild(0).getText());
                final Param param = Param.builder()
                    .type(type)
                    .name(childChild.getChild(0).getChild(1).getChild(0).getChild(0).getText())
                    .initValue(childChild.getChild(0).getChild(1).getChild(0).getChild(2).getText())
                    .scope(scope.currentLevel())
                    .build();

                expressions.add(param);

            } else if (childChild.getChild(0) instanceof StatementWithoutTrailingSubstatementContext) {
                expressions.add(Expression.builder()
                    .expression(childChild.getChild(0).getText().replace(";", ""))
                    .scope(scope.currentLevel())
                    .build());
            }

        });

        callback.accept(expressions);
    }


}



