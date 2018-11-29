package unisinos.tradutores.java2python.listener;


import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import lombok.Getter;
import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.data.Element;
import unisinos.tradutores.java2python.domain.ScopeLevel;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.ForStatementContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.MethodDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FieldDeclarationContext;
import unisinos.tradutores.java2python.listener.builder.BlockBuilder;
import unisinos.tradutores.java2python.listener.builder.ClassBuilder;
import unisinos.tradutores.java2python.listener.builder.ForBuilder;
import unisinos.tradutores.java2python.listener.builder.IfBuilder;
import unisinos.tradutores.java2python.listener.builder.MethodBuilder;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private ScopeLevel scope = new ScopeLevel();

    private final ClassBuilder classBuilder = ClassBuilder.builder().build();
    private final MethodBuilder methodBuilder = MethodBuilder.builder().scope(scope).build();
    private final ForBuilder forBuilder = ForBuilder.builder().scope(scope).build();
    private final IfBuilder ifBuilder = IfBuilder.builder().scope(scope).build();
    private final BlockBuilder blockBuilder = BlockBuilder.builder().scope(scope).build();


    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        classBuilder.build(ctx, result -> {
            for (int i=1; i < ctx.getChild(3).getChildCount() -1; i++){
                if(ctx.getChild(3).getChild(i).getChild(0).getChild(0) instanceof FieldDeclarationContext){
                    result.addAtr(ctx.getChild(3).getChild(i).getChild(0).getChild(0).getChild(2).getText());
                }
            }
            classes.add(result);
            System.out.println("CLASSE: " + result);
        });
        this.scope.up();
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        classBuilder.build(ctx, result -> {
            classes.add(result);
            System.out.println("ENUM: " + result);
        });
        this.scope.up();
    }

    @Override public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        //System.out.println(ctx);
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        methodBuilder.build(ctx, result -> {
            addElement(result);
            System.out.println("\tMETHOD:  " + result);
        });
        this.scope.up();
    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {
        forBuilder.build(ctx, result -> {
            addElement(result);
            System.out.println("\tFOR:  " + result);
        });
        this.scope.up();
    }


    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        ifBuilder.build(ctx, result -> {
            System.out.println("\tIF THEN:  " + result);
            addElement(result);
        });
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        System.out.println("\tIF THEN ELSE:  " + ctx.getText());
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
        blockBuilder.build(ctx, result -> {
            result.forEach(this::addElement);
            result.forEach(_result ->{
                System.out.println("BLOCK: " + _result);
            });
        });
//        System.out.print("\t\tDETAILS: ");
//        printa_children(ctx.children);
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


    @Override
    public void exitNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        this.scope.down();
    }

    @Override
    public void exitEnumDeclaration(final EnumDeclarationContext ctx) {
        this.scope.down();
    }

    @Override
    public void exitForStatement(final ForStatementContext ctx) {
        this.scope.down();
    }

    @Override
    public void exitMethodDeclaration(final MethodDeclarationContext ctx) {
        this.scope.down();
    }
}
