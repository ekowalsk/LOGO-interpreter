package Nodes;

import Visitors.Visitor;

public class Parameter implements Node {
    private Var var;
    private VariableType type;
    public Parameter(Var var, VariableType type) {
        this.var = var;
        this.type = type;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Var getVar() { return var; }
    public VariableType getType() { return type; }
    public Object getOutput() { return null; }
    public void setOutput(Object output) {}
}
