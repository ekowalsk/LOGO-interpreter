package Nodes.ConditionalCommands;

import Nodes.Node;

public class While extends ConditionalCommand {
    public While (Node expression, Node block) {
        super(expression, block);
    }
}
