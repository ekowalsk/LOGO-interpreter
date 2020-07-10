package Nodes;

import Visitors.Visitor;

public class Variable implements Node {
    private final String name;
    public Variable(String name) {
        this.name = name;
    }
    public void accept (Visitor v) throws Exception {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
}
