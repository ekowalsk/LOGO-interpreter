package Nodes;

import Visitors.Visitor;

public class Assignment implements Node {
    private Variable variable;
    private VariableType type;
    private Node expression;
    public Assignment (Variable variable, VariableType type, Node expression) {
        this.variable = variable;
        this.type = type;
        this.expression = expression;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
