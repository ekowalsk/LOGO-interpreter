package Nodes.ChildrenNodes;

import Nodes.Node;
import Visitors.Visitor;

import java.util.LinkedList;
import java.util.ListIterator;

public class ChildrenNode implements Node {
    private LinkedList<Node> children;
    private Object output;
    public ChildrenNode() {
        children = new LinkedList<>();
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public void add(Node node) {
        children.add(node);
    }
    public ListIterator getChildren() {
        return children.listIterator(0);
    }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
}
