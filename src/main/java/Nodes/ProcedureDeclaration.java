package Nodes;

import Visitors.Visitor;

import java.util.LinkedList;

public class ProcedureDeclaration implements Node {
    private String name;
    private LinkedList<Node> children;
    private LinkedList<Parameter> parameters;
    public ProcedureDeclaration (String name, LinkedList<Node> children, LinkedList<Parameter> parameters) {
        this.name = name;
        this.children = (children == null) ? new LinkedList<>() : children;
        this.parameters = (parameters == null) ? new LinkedList<>() : parameters;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public String getName() {
        return name;
    }
}
