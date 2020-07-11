package Nodes;

import Visitors.Visitor;

public class Assignment implements Node {
    private final Var var;
    private final VariableType type;
    private final Node expression;
    private Object output;
    public Assignment (Var var, VariableType type, Node expression) {
        this.var = var;
        this.type = type;
        this.expression = expression;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Var getVar() { return var; }
    public VariableType getType() {
        return type;
    }
    public Node getExpression() {
        return expression;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
