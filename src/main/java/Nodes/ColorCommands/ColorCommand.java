package Nodes.ColorCommands;

import Nodes.Color;
import Nodes.Node;
import Visitors.Visitor;

public abstract class ColorCommand implements Node {
    private final Color color;
    public ColorCommand(Color color) {
        this.color = color;
    }
    @Override
    public void accept (Visitor v) {
        v.visit(this);
    }
}
