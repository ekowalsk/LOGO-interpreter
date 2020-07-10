package Nodes;

import Visitors.Visitor;

public class Number implements Node {
    private String value;
    private Object output;
    public Number (String value) {
        this.value = value;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public String getValue() { return value; }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
