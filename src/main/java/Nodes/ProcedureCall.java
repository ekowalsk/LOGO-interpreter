package Nodes;

import Visitors.Visitor;

import java.util.LinkedList;

public class ProcedureCall implements Node {
    private String procedureName;
    private LinkedList<Node> arguments;
    public ProcedureCall(String procedureName, LinkedList<Node> arguments) {
        this.procedureName = procedureName;
        this.arguments = (arguments == null) ? new LinkedList<>() : arguments;
    }
    public void accept(Visitor v) {
        v.visit(this);
    }
    public Object getOutput() { return null; }
    public void setOutput(Object output) {}
}
