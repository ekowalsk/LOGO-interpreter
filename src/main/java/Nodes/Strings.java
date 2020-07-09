package Nodes;

import Visitors.Visitor;

public class Strings implements Node {
    private String value;
    public Strings(String value) {
        this.value = value;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public String getValue() {
        return value;
    }
}
