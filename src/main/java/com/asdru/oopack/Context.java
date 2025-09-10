package com.asdru.oopack;

import com.asdru.oopack.internal.ContextItem;

import java.util.Stack;

public class Context {
    private static final Stack<ContextItem> stack = new Stack<>();

    public void push(ContextItem item){
        stack.push(item);
    }
    public ContextItem pop(){
        return stack.pop();
    }
    public ContextItem peek(){
        return stack.peek();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
