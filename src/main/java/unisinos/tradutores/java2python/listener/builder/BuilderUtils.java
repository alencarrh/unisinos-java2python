package unisinos.tradutores.java2python.listener.builder;

import java.util.function.Consumer;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class BuilderUtils {

    public static void forChildrenOf(final ParseTree parent, final Consumer<TerminalNodeImpl> consumer) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            ParseTree child = parent.getChild(i);
            if (child instanceof TerminalNodeImpl) {
                consumer.accept((TerminalNodeImpl) child);
                continue;
            }
            forChildrenOf(child, consumer);
        }
    }

    public static ParseTree getFirstChildN(ParseTree parent, int n) {
        if (n == 0) {
            return parent;
        }
        return getFirstChildN(parent.getChild(0), n - 1);
    }


}
