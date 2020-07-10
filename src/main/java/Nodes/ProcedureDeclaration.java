package Nodes;

import Visitors.Visitor;

import java.util.LinkedList;
import java.util.ListIterator;

public class ProcedureDeclaration implements Node {
    private String name;
    private LinkedList<Node> children;
    private LinkedList<Parameter> parameters;
    public ProcedureDeclaration (String name, LinkedList<Node> children, LinkedList<Parameter> parameters) {
        this.name = name;
        this.children = (children == null) ? new LinkedList<>() : children;
        this.parameters = (parameters == null) ? new LinkedList<>() : parameters;
    }
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
    public ListIterator<Parameter> getParameters() { return parameters.listIterator(0); }
    public ListIterator<Node> getChildren() { return children.listIterator(0); }
    public Object getOutput() { return null; }
    public void setOutput(Object output) {}
}
