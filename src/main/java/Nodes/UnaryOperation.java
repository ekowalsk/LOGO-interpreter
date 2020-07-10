package Nodes;

import Visitors.Visitor;

public class UnaryOperation implements Node {
    private final String operator;
    private final Node expression;
    private Object output;
    public UnaryOperation(String operator, Node expression) {
        this.operator = operator;
        this.expression = expression;
    }
    public void accept (Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getExpression() { return expression; }
    public Object getOutput() {return output; }
    public void setOutput(Object output) { this.output = output; }
}
