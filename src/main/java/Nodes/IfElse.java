package Nodes;

import Visitors.Visitor;

public class IfElse implements Node {
    private Node condition;
    private Node trueBlock;
    private Node falseBlock;
    public IfElse(Node condition, Node trueBlock, Node falseBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getCondition() {
        return condition;
    }
    public Node getTrueBlock() {
        return trueBlock;
    }
    public Node getFalseBlock() {
        return falseBlock;
    }
}
