package Nodes;

import Visitors.Visitor;

public class BinaryOperation extends Output<Float> implements Node {
    private Node leftExpression;
    private Node rightExpression;
    private String operator;
    public BinaryOperation (Node leftExpression, String operator, Node rightExpression) {
        super((float) 0.0);
        this.leftExpression = leftExpression;
        this.operator = operator;
        this.rightExpression = rightExpression;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
