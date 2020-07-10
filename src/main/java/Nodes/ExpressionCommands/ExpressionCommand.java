package Nodes.ExpressionCommands;

import Nodes.Node;
import Visitors.Visitor;

public class ExpressionCommand implements Node {
    protected Node expression;
    private Object output;
    public ExpressionCommand(Node expression) {
        this.expression = expression;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getExpression() {
        return expression;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
