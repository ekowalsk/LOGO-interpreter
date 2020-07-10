package Nodes.SimpleCommands;

import Nodes.Node;
import Visitors.Visitor;

public abstract class SimpleCommand implements Node {
    public void accept(Visitor v) throws Exception {
        v.visit(this);
    }
    public Object getOutput() { return null; }
    public void setOutput(Object output){}
}
