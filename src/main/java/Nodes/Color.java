package Nodes;

import Nodes.Node;
import Visitors.Visitor;

public class Color implements Node {
    public enum ColorValue {
        RED, BLUE, GREEN, YELLOW
    }
    private ColorValue value;
    public Color (ColorValue value) {
        this.value = value;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
