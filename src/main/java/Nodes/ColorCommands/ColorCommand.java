package Nodes.ColorCommands;

import Nodes.Color;
import Nodes.Node;
import Visitors.Visitor;

public abstract class ColorCommand implements Node {
    private final Color color;
    private Object output;
    public ColorCommand(Color color) {
        this.color = color;
    }
    public void accept (Visitor v) {
        v.visit(this);
    }
    public Color getColor() {
        return color;
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
