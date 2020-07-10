package Nodes;

import Visitors.Visitor;

public class Color implements Node {
    private final String colorName;
    private Object output;
    public Color (String colorName) {
        this.colorName = colorName;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public String getName() {return colorName; }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
