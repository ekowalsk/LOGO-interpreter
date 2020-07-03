package Nodes;

import Visitors.Visitor;

public class Number implements Node {
    private String value;
    public Number (String value) {
        this.value = value;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
