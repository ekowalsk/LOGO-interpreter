package Nodes.ExpressionCommands;

import Nodes.Node;
import Visitors.Visitor;

public abstract class ExpressionCommand implements Node {
    protected Node expression;
    public ExpressionCommand(Node expression) {
        this.expression = expression;
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Node getExpression() {
        return expression;
    }
}
