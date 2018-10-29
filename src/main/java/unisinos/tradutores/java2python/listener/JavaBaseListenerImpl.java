package unisinos.tradutores.java2python.listener;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.data.ClassBody;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private Class.ClassBuilder currentClass;
    private ClassBody.ClassBodyBuilder currentBodyClass;

    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        if (currentClass != null) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(false).name(ctx.children.get(2).getText());
        System.out.println("Classe normal encontrada: " + ctx.children.get(2).getText());
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        if (currentClass != null) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(true).name(ctx.children.get(1).getText());
        System.out.println("Enum encontrada: " + ctx.children.get(1).getText());
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
//        System.out.println("enterMethodDeclarator:  " + ctx.getText());
//        System.out.println("    getChildCount():  " + ctx.getChildCount());
//        System.out.println("    children:  " + ctx.children);

        //Criar métodos aqui

    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
//        System.out.println("enterMethodInvocation:  " + ctx.getText());

        //Criar um comando/expressão aqui
    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        System.out.println("enterMethodInvocation:  " + ctx.getText());
    }

    @Override
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {
    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {
    }

    @Override
    public void enterForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {
    }

    @Override
    public void enterWhileStatement(Java8Parser.WhileStatementContext ctx) {
    }

    @Override
    public void enterWhileStatementNoShortIf(Java8Parser.WhileStatementNoShortIfContext ctx) {
    }

    @Override
    public void enterDoStatement(Java8Parser.DoStatementContext ctx) {
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
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
}
