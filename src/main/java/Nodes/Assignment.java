package Nodes;

import Visitors.Visitor;

public class Assignment implements Node {
    private Variable variable;
    private VariableType type;
    private Node expression;
    public Assignment (Variable variable, VariableType type, Node expression) {
        this.variable = variable;
        this.type = type;
        this.expression = expression;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Variable getVariable() {
        return variable;
    }
    public VariableType getType() {
        return type;
    }
    public Node getExpression() {
        return expression;
    }
}
