package Nodes;

import Visitors.Visitor;

import java.util.LinkedList;

public class Program implements Node {
    protected LinkedList<Node> children;
    public Program() {
        children = new LinkedList<>();
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
}
