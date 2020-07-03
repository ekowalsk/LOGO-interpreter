package Nodes.SimpleCommands;

import Nodes.Node;
import Visitors.Visitor;

public abstract class SimpleCommand implements Node {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
