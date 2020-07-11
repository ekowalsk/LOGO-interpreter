package Nodes;

import Visitors.Visitor;

public class Var implements Node {
    private final String name;
    private Object output;
    public Var(String name) {
        this.name = name;
    }
    public void accept (Visitor v) throws Exception {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
