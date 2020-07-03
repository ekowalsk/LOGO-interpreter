package Nodes.ConditionalCommands;

import Nodes.Node;
import Visitors.Visitor;

public abstract class ConditionalCommand implements Node {
    protected Node expression;
    protected Node block;
    public ConditionalCommand(Node expression, Node block) {
        this.expression = expression;
        this.block = block;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Node getBlock() {
        return block;
    }
    public Node getExpression() {
        return expression;
    }
}
