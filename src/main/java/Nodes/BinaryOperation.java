package Nodes;

import Visitors.Visitor;

public class BinaryOperation implements Node {
    private final Node leftExpression;
    private final Node rightExpression;
    private final String operator;
    private Object output;
    public BinaryOperation (Node leftExpression, String operator, Node rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = operator;
        this.rightExpression = rightExpression;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getLeftExpression() {
        return leftExpression;
    }
    public Node getRightExpression() {
        return rightExpression;
    }
    public String getOperator() {
        return operator;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
