package Nodes;

import Visitors.Visitor;

public class BinaryOperation implements Node {
    private Node leftExpression;
    private Node rightExpression;
    private String operator;
    public BinaryOperation (Node leftExpression, String operator, Node rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = operator;
        this.rightExpression = rightExpression;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
