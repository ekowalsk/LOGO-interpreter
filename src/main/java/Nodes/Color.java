package Nodes;

import Visitors.Visitor;

public class Color implements Node {
    private String colorName;
    public Color (String colorName) {
        this.colorName = colorName;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
