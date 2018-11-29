package unisinos.tradutores.java2python.listener.builder;

import static org.apache.commons.lang3.BooleanUtils.isFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.Builder;
import unisinos.tradutores.java2python.data.Method;
import unisinos.tradutores.java2python.data.Param;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FormalParameterListContext;

@Builder
public class MethodBuilder {

    private final ScopeLevel scope;

    public void build(Java8Parser.MethodDeclaratorContext ctx, Consumer<Method> callback) {
        final boolean modifier = ctx.getParent().getParent().getChild(0).getText().equals("public");
        final VariableType returnType = VariableType
            .fromText(ctx.getParent().getParent().getChild(1).getChild(0).getText());

        final Method.MethodBuilder methodBuilder = Method.builder()
            .modifier(modifier)
            .returnType(returnType)
            .name(ctx.getChild(0).getText());

        if (ctx.getChild(2) instanceof FormalParameterListContext) {
            final Param.ParamBuilder param = Param.builder();
            final List<String> temp = new ArrayList<>();
            BuilderUtils.forChildrenOf(ctx.getChild(2), child -> {
                if (isFalse(child.getText().equals(","))) {
                    temp.add(child.getText());
                    return;
                }
                methodBuilder.param(param.name(temp.get(1)).type(VariableType.fromText(temp.get(0))).build());
                temp.clear();
            });
            methodBuilder.param(param.name(temp.get(1)).type(VariableType.fromText(temp.get(0))).build());
        }

        final Method method = methodBuilder.build();

        callback.accept(method);
    }

}
