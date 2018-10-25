package unisinos.tradutores.java2python.listener;


import lombok.Getter;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
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
}
