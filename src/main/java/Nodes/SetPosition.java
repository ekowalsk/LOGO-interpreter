package Nodes;

import Visitors.Visitor;

public class SetPosition implements Node {
    private Node expressionX;
    private Node expressionY;
    public SetPosition(Node expressionX, Node expressionY) {
        this.expressionX = expressionX;
        this.expressionY = expressionY;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getExpressionX() {
        return expressionX;
    }
    public Node getExpressionY() {
        return expressionY;
    }
    public Object getOutput() { return null; }
    public void setOutput(Object output) {}
}
