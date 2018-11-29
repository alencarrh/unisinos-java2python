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
import unisinos.tradutores.java2python.data.Element;
import unisinos.tradutores.java2python.data.Expression;
import unisinos.tradutores.java2python.data.GenericBody;
import unisinos.tradutores.java2python.data.GenericStatement;
import unisinos.tradutores.java2python.data.Method;
import unisinos.tradutores.java2python.data.Param;
import unisinos.tradutores.java2python.data.RepetitionStructure;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.BlockStatementsContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ExpressionContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForInitContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForStatementContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForUpdateContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FormalParameterListContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.MethodDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private List<Element> elements;
    private ScopeLevel scope = new ScopeLevel();

    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        this.elements = new ArrayList<>();

        /*
          Está sendo criado uma classe com uma lista vazia, mas essa lista será atualizada conforme o processo vai
          acontecendo.
         */
        final Class clazz = Class.builder()
            .enumClass(false)
            .name(ctx.children.get(2).getText())
            .build();

        classes.add(clazz);

        this.scope.up();

        System.out.println("CLASSE: " + clazz);
    }

    @Override
    public void exitNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        this.scope.down();
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        this.elements = new ArrayList<>();

        /*
          Está sendo criado uma classe com uma lista vazia, mas essa lista será atualizada conforme o processo vai
          acontecendo.
         */
        final Class clazz = Class.builder()
            .enumClass(true)
            .name(ctx.children.get(1).getText())
            .elements(elements)
            .build();

        classes.add(clazz);

        this.scope.up();

        System.out.println("ENUM: " + clazz);
    }

    @Override
    public void exitEnumDeclaration(final EnumDeclarationContext ctx) {
        this.scope.down();
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
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
            forChildrenOf(ctx.getChild(2), child -> {
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

        addElement(method);
        System.out.println("\tMETHOD:  " + method);

        this.scope.up();
    }

    @Override
    public void exitMethodDeclaration(final MethodDeclarationContext ctx) {
        this.scope.down();
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
            .scope(this.scope.currentLevel())
            .build();

        addElement(forRepetitionStructure);
        this.scope.up();
        System.out.println("\tFOR:  " + forRepetitionStructure);
    }

    @Override
    public void exitForStatement(final ForStatementContext ctx) {
        this.scope.down();
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
        return classes;
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

    private void addElement(final Element element) {
        this.classes.get(this.classes.size() - 1).addElement(element);
    }

}
