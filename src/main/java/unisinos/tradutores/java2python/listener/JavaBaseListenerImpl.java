package unisinos.tradutores.java2python.listener;


import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import lombok.Getter;
import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.data.ClassBody;
import unisinos.tradutores.java2python.data.Constructor;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.data.GenericBody;
import unisinos.tradutores.java2python.data.GenericStatement;
import unisinos.tradutores.java2python.data.Method;
import unisinos.tradutores.java2python.data.Param;
import unisinos.tradutores.java2python.data.RepetitionStructure;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.BlockStatementsContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ExpressionContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForInitContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForUpdateContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FormalParameterListContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private Class.ClassBuilder currentClass;
    private ClassBody.ClassBodyBuilder currentBodyClass;
    private Constructor.ConstructorBuilder currentConstructor;
    private Method.MethodBuilder currentMethod;
//    private Scope currentScope; //vai lidar com o scopo atual, então vai adicionar sempre aqui e essa classe sabe quem é o scope

    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        if (nonNull(currentMethod)) {
            currentBodyClass.method(currentMethod.build());
        }
        if (nonNull(currentClass)) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(false).name(ctx.children.get(2).getText());
        currentBodyClass = ClassBody.builder();
        System.out.println("CLASSE: " + currentClass.build());
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        if (nonNull(currentMethod)) {
            currentBodyClass.method(currentMethod.build());
        }
        if (nonNull(currentClass)) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(true).name(ctx.children.get(1).getText());
        currentBodyClass = ClassBody.builder();
        System.out.println("CLASSE ENUM: " + currentClass.build());
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        final boolean modifier = ctx.getParent().getParent().getChild(0).getText().equals("public");
        final VariableType returnType = VariableType
            .fromText(ctx.getParent().getParent().getChild(1).getChild(0).getText());

        currentMethod = Method.builder()
            .modifier(modifier)
            .returnType(returnType)
            .name(ctx.getChild(0).getText())
        ;

        if (ctx.getChild(2) instanceof FormalParameterListContext) {
            final Param.ParamBuilder param = Param.builder();
            final List<String> temp = new ArrayList<>();
            forChildrenOf(ctx.getChild(2), child -> {
                if (isFalse(child.getText().equals(","))) {
                    temp.add(child.getText());
                    return;
                }
                currentMethod.param(param.name(temp.get(1)).type(VariableType.fromText(temp.get(0))).build());
                temp.clear();
            });
            currentMethod.param(param.name(temp.get(1)).type(VariableType.fromText(temp.get(0))).build());
        }
        currentBodyClass.method(currentMethod.build());
        System.out.println("\tMETHOD:  " + currentMethod.build());
    }

    private void forChildrenOf(final ParseTree parent, final Consumer<TerminalNodeImpl> consumer) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            ParseTree child = parent.getChild(i);
            if (child instanceof TerminalNodeImpl) {
                consumer.accept((TerminalNodeImpl) child);
                continue;
            }
            forChildrenOf(child, consumer);
        }
    }

    private void printa_children(final List<ParseTree> children) {
        children.forEach(this::printa_child);
        System.out.println();
    }


    private void printa_child(final ParseTree c) {
        if (c instanceof TerminalNodeImpl) {
            System.out.print(c.getText() + " ");
        } else {
            for (int i = 0; i < c.getChildCount(); i++) {
                printa_child(c.getChild(i));
            }
        }
    }


    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
//        System.out.println("\tMETHOD CALL:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);

        //Criar um comando/expressão aqui
    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
//        System.out.println("\tMETHOD CALL:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);

    }

    @Override
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
//        System.out.println("\tMETHOD CALL:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {
//        System.out.println("\tBLOCK:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {
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
        forChildrenOf(statementContext, child -> {
            finalExpression.append(child.getText() + " ");
        });

        asList(finalExpression.toString().split("; ")).forEach(expression -> {
            GenericStatement genericExpression = null;
            final String[] expressionParts = expression.split(" ");

            if (expressionParts[1].equals("=")) {
                genericExpression = Expression.builder().expression(expression).build();
            } else if (expressionParts[2].equals("=")) {
                //é um Param
                genericExpression = Param.builder()
                    .type(VariableType.fromText(expressionParts[0]))
                    .name(expressionParts[1])
                    .initValue(Arrays.stream(expressionParts).skip(0).skip(1).skip(2).toArray(String[]::new))
                    .build();

            }

            if (nonNull(genericExpression)) {
                forBody.expressoin(genericExpression);
            }


        });

        final RepetitionStructure forRepetitionStructure = RepetitionStructure.builder()
            .initVariables(forInitVariable)
            .keepRunningCondition(forCondition)
            .updateVariables(forUpdate)
            .body(forBody.build())
            .build();

            System.out.println("\tFOR:  " + forRepetitionStructure);
    }

    @Override
    public void enterForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {
//        System.out.println("\tWHILE:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterWhileStatement(Java8Parser.WhileStatementContext ctx) {
//        System.out.println("\tWHILE:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterWhileStatementNoShortIf(Java8Parser.WhileStatementNoShortIfContext ctx) {
//        System.out.println("\tWHILE:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
//        System.out.println("\tIF THEN:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);

    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
//        System.out.println("\tIF THEN ELSE:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
//        System.out.println("\tIF THEN ELSE:  " + ctx.getText());
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
    }

    @Override
    public void enterCatches(Java8Parser.CatchesContext ctx) {

    }

    @Override
    public void enterConditionalExpression(Java8Parser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void enterSwitchLabel(Java8Parser.SwitchLabelContext ctx) {
    }

    public List<Class> build() {
        //adiciona os últimos valores para a última classe

        if (nonNull(currentClass)) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        System.out.println(classes);
        return classes;
    }
}
