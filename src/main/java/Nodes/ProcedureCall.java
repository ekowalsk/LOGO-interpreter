package Nodes;

import Visitors.Visitor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ProcedureCall implements Node {
    private String procedureName;
    private LinkedList<Node> arguments;
    private Object output;
    public ProcedureCall(String procedureName, LinkedList<Node> arguments) {
        this.procedureName = procedureName;
        this.arguments = (arguments == null) ? new LinkedList<>() : arguments;
    }
    public void accept(Visitor v)throws Exception  {
        v.visit(this);
    }
    public String getName() { return procedureName; }
    public Object getOutput() { return output; }
    public void setOutput(Object output) { this.output = output; }
    public int argumentsCount() { return arguments.size(); }
    public Node getArgument (int index) { return arguments.get(index); }
}
