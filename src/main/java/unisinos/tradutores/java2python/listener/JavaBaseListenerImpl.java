package unisinos.tradutores.java2python.listener;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import lombok.Getter;
import unisinos.tradutores.java2python.data.Class;
import unisinos.tradutores.java2python.data.ClassBody;
import unisinos.tradutores.java2python.data.Constructor;
import unisinos.tradutores.java2python.data.Method;
import unisinos.tradutores.java2python.domain.VariableType;
import unisinos.tradutores.java2python.gramatica.Java8BaseListener;
import unisinos.tradutores.java2python.gramatica.Java8Parser;
import unisinos.tradutores.java2python.gramatica.Java8Parser.EnumDeclarationContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.FormalParameterListContext;
import unisinos.tradutores.java2python.gramatica.Java8Parser.NormalClassDeclarationContext;

@Getter
public class JavaBaseListenerImpl extends Java8BaseListener {

    private List<Class> classes = new ArrayList<>();
    private Class.ClassBuilder currentClass;
    private ClassBody.ClassBodyBuilder currentBodyClass;
    private Constructor.ConstructorBuilder currentConstructor;
    private Method.MethodBuilder currentMethod;

    @Override
    public void enterNormalClassDeclaration(final NormalClassDeclarationContext ctx) {
        if (currentClass != null) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(false).name(ctx.children.get(2).getText());
        currentBodyClass = ClassBody.builder();
        System.out.println("CLASSE: " + ctx.children.get(2).getText());
    }

    @Override
    public void enterEnumDeclaration(final EnumDeclarationContext ctx) {
        if (currentClass != null) {
            classes.add(currentClass.body(currentBodyClass.build()).build());
        }
        currentClass = Class.builder().enumClass(true).name(ctx.children.get(1).getText());
        System.out.println("CLASSE ENUM: " + ctx.children.get(1).getText());
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {

        //TODO comentar system.outs e printa_children
        System.out.println("\tMETHOD:  " + ctx.children.get(0).getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);

        if (currentMethod != null) {
            currentBodyClass.method(currentMethod.build());
        }

        final boolean modifier = ctx.getParent().getParent().getChild(0).getText().equals("public");
        final VariableType returnType = VariableType
            .fromText(ctx.getParent().getParent().getChild(1).getChild(0).getText());

        currentMethod = Method.builder()
            .modifier(modifier)
            .returnType(returnType)
            .name(ctx.getChild(0).getText())
        ;

        if (ctx.getChild(2) instanceof FormalParameterListContext) {
            forChildrenOf(ctx.getChild(2), child -> {
                // TODO Criar 'Param' e adicionar ao método
                // O que vai ter aqui?
                // Para o 'method4'
                // 1 - String
                // 2 - a
                // 3 ,
                // 4 - int
                // 5 - b
                // 6 - ,
                // 7 - ...
                System.out.println("param: " + child);
            });
        }
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

//        System.out.println("N~~ÃO");

//        if (c instanceof FormalParameterListContext) {
//            printa_children(((FormalParameterListContext) c).children);
//            return;
//        }
//        if (c instanceof FormalParametersContext) {
//            printa_children(((FormalParametersContext) c).children);
//            return;
//        }
//        if (c instanceof FormalParameterContext) {
//            printa_children(((FormalParameterContext) c).children);
//            return;
//        }
//        if (c instanceof UnannTypeContext) {
//            printa_children(((UnannTypeContext) c).children);
//            return;
//        }
//        if (c instanceof UnannReferenceTypeContext) {
//            printa_children(((UnannReferenceTypeContext) c).children);
//            return;
//        }
//        if (c instanceof UnannClassOrInterfaceTypeContext) {
//            printa_children(((UnannClassOrInterfaceTypeContext) c).children);
//            return;
//        }
//        if (c instanceof UnannClassType_lfno_unannClassOrInterfaceTypeContext) {
//            printa_children(((UnannClassType_lfno_unannClassOrInterfaceTypeContext) c).children);
//            return;
//        }
//        if (c instanceof VariableDeclaratorIdContext) {
//            printa_children(((VariableDeclaratorIdContext) c).children);
//            return;
//        }
//        if (c instanceof UnannPrimitiveTypeContext) {
//            printa_children(((UnannPrimitiveTypeContext) c).children);
//            return;
//        }
//        if (c instanceof NumericTypeContext) {
//            printa_children(((NumericTypeContext) c).children);
//            return;
//        }
//        if (c instanceof IntegralTypeContext) {
//            printa_children(((IntegralTypeContext) c).children);
//            return;
//        }
//        if (c instanceof LastFormalParameterContext) {
//            printa_children(((LastFormalParameterContext) c).children);
//            return;
//        }
//        //em teoria, não deveria chegar aqui
//        System.out.print("Não deveria ter chegeado aqui | " + c.getText());
    }


    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        System.out.println("\tMETHOD CALL:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);

        //Criar um comando/expressão aqui
    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        System.out.println("\tMETHOD CALL:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);

    }

    @Override
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        System.out.println("\tMETHOD CALL:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {
        System.out.println("\tBLOCK:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {
        System.out.println("\tFOR:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {
        System.out.println("\tWHILE:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterWhileStatement(Java8Parser.WhileStatementContext ctx) {
        System.out.println("\tWHILE:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterWhileStatementNoShortIf(Java8Parser.WhileStatementNoShortIfContext ctx) {
        System.out.println("\tWHILE:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        System.out.println("\tIF THEN:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);

    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        System.out.println("\tIF THEN ELSE:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
        System.out.println("\tIF THEN ELSE:  " + ctx.getText());
        System.out.print("\t\tDETAILS: ");
        printa_children(ctx.children);
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
