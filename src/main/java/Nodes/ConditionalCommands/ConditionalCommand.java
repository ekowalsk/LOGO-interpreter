package Nodes.ConditionalCommands;

import Nodes.Node;
import Visitors.Visitor;

public abstract class ConditionalCommand implements Node {
    protected Node expression;
    protected Node block;
    private Object output;
    public ConditionalCommand(Node expression, Node block) {
        this.expression = expression;
        this.block = block;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getBlock() {
        return block;
    }
    public Node getExpression() {
        return expression;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
