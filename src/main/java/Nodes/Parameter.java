package Nodes;

import Visitors.Visitor;

public class Parameter implements Node {
    private Variable var;
    private VariableType type;
    public Parameter(Variable var, VariableType type) {
        this.var = var;
        this.type = type;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Variable getVar() {
        return var;
    }
    public VariableType getType() {
        return type;
    }
}
