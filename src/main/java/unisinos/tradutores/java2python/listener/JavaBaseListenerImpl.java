package unisinos.tradutores.java2python.listener;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import unisinos.tradutores.java2python.gramatica.Java8Parser.IfThenStatementContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.MethodDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FieldDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.WhileStatementContext;
import unisinos.tradutores.java2python.listener.builder.BlockBuilder;
import unisinos.tradutores.java2python.listener.builder.ClassBuilder;
import unisinos.tradutores.java2python.listener.builder.ForBuilder;
import unisinos.tradutores.java2python.listener.builder.IfBuilder;
import unisinos.tradutores.java2python.listener.builder.MethodBuilder;
import unisinos.tradutores.java2python.listener.builder.WhileBuilder;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private ScopeLevel scope = new ScopeLevel();

    private final ClassBuilder classBuilder = ClassBuilder.builder().build();
    private final MethodBuilder methodBuilder = MethodBuilder.builder().scope(scope).build();
    private final ForBuilder forBuilder = ForBuilder.builder().scope(scope).build();
    private final WhileBuilder whileBuilder = WhileBuilder.builder().scope(scope).build();
    private final IfBuilder ifBuilder = IfBuilder.builder().scope(scope).build();
    private final BlockBuilder blockBuilder = BlockBuilder.builder().scope(scope).build();


    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        this.scope.up();
        classBuilder.build(ctx, result -> {
            for (int i = 1; i < ctx.getChild(3).getChildCount() - 1; i++) {
                if (ctx.getChild(3).getChild(i).getChild(0).getChild(0) instanceof FieldDeclarationContext) {
                    result.addAtr(ctx.getChild(3).getChild(i).getChild(0).getChild(0).getChild(2).getText());
                }
            }
            classes.add(result);
//            System.out.println("CLASSE: " + result);
        });
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        this.scope.up();
        classBuilder.build(ctx, result -> {
            classes.add(result);
//            System.out.println("ENUM: " + result);
        });
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        this.scope.up();
        methodBuilder.build(ctx, this::addElement);
    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {
        forBuilder.build(ctx, this::addElement);
        this.scope.up();
    }


    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        ifBuilder.build(ctx, this::addElement);
        this.scope.up();
    }


    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        //Criar um comando/expressão aqui
    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {
        blockBuilder.build(ctx, this::addElement);
    }


    @Override
    public void enterForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {
    }

    @Override
    public void enterWhileStatement(Java8Parser.WhileStatementContext ctx) {
        whileBuilder.build(ctx, this::addElement);
        this.scope.up();
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
    public void exitWhileStatement(final WhileStatementContext ctx) {
        this.scope.down();
    }

    @Override
    public void exitMethodDeclaration(final MethodDeclarationContext ctx) {
        this.scope.down();
    }

    @Override
    public void exitIfThenStatement(final IfThenStatementContext ctx) {
        this.scope.down();
    }
}
