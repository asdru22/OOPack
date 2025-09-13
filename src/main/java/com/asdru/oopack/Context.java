package com.asdru.oopack;

import com.asdru.oopack.internal.ContextItem;

import java.util.Optional;
import java.util.Stack;

public class Context {
    private static final Stack<ContextItem> stack = new Stack<>();

    public static void push(ContextItem item) {
        stack.push(item);
    }

    public static ContextItem pop() {
        return stack.pop();
    }

    public static ContextItem peek() {
        return stack.peek();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static ContextItem exit() {
        return stack.pop();
    }

    public static void clear() {
        stack.forEach(ContextItem::exit);
        stack.clear();
    }

    public static String getFunctionPath() {
        if (stack.peek() instanceof Module mod) {
            return mod.getFunctionPath();
        }
        return "";
    }

    public static Namespace getActiveNamespace() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            ContextItem item = stack.get(i);
            if (item instanceof Namespace ns) {
                return ns;
            }
        }
        throw new IllegalStateException("No active Namespace found in the context stack.");
    }
}
