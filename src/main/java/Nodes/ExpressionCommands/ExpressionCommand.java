package Nodes.ExpressionCommands;

import Nodes.Node;
import Nodes.Output;
import Visitors.Visitor;

public class ExpressionCommand extends Output<Float> implements Node {
    protected Node expression;
    public ExpressionCommand(Node expression) {
        super((float) 0.0);
        this.expression = expression;
    }
    @Override
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getExpression() {
        return expression;
    }
}
