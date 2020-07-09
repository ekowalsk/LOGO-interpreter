package Nodes.ChildrenNodes;

import Nodes.Node;
import Visitors.Visitor;

import java.util.LinkedList;
import java.util.ListIterator;

public class ChildrenNode implements Node {
    private LinkedList<Node> children;
    public ChildrenNode() {
        children = new LinkedList<>();
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public void add(Node node) {
        children.add(node);
    }
    public ListIterator getChildren() {
        return children.listIterator(0);
    }
}
