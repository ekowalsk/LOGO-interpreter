package Nodes;

import Visitors.Visitor;

public class Variable implements Node {
    private String name;
    public Variable(String name) {
        this.name = name;
    }
    public void accept (Visitor v) {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
}
