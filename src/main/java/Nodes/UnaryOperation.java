package Nodes;

import Visitors.Visitor;

public class UnaryOperation implements Node {
    private String operator;
    private Node expression;
    public UnaryOperation(String operator, Node expression) {
        this.operator = operator;
        this.expression = expression;
    }
    public void accept (Visitor v) {
        v.visit(this);
    }
}