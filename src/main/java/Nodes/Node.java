package Nodes;

import Visitors.Visitor;

public interface Node {
    void accept(Visitor v) throws Exception;
}
