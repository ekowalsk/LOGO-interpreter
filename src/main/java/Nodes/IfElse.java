package Nodes;

import Visitors.Visitor;

public class IfElse implements Node {
    private final Node condition;
    private final Node trueBlock;
    private final Node falseBlock;
    private Object output;
    public IfElse(Node condition, Node trueBlock, Node falseBlock) {
        this.condition = condition;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Node getCondition() { return condition; }
    public Node getTrueBlock() { return trueBlock; }
    public Node getFalseBlock() { return falseBlock; }
    public Object getOutput() {return output; }
    public void setOutput(Object output) { this.output = output; }
}
