package Nodes;

import Visitors.Visitor;

public class SetPosition implements Node {
    private Node expressionX;
    private Node expressionY;
    public SetPosition(Node expressionX, Node expressionY) {
        this.expressionX = expressionX;
        this.expressionY = expressionY;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Node getExpressionX() {
        return expressionX;
    }
    public Node getExpressionY() {
        return expressionY;
    }
}
