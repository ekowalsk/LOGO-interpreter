package Nodes;

import Visitors.Visitor;

public class VariableType implements Node {
    private String name;
    public VariableType (String name) {
        this.name = name;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
}
