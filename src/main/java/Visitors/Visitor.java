package Visitors;

import Nodes.*;
import Nodes.ChildrenNodes.ChildrenNode;
import Nodes.ColorCommands.ColorCommand;
import Nodes.ConditionalCommands.ConditionalCommand;
import Nodes.ExpressionCommands.ExpressionCommand;
import Nodes.SimpleCommands.SimpleCommand;
import Nodes.Number;

public interface Visitor {
    void visit(SimpleCommand simpleCommand);
    void visit(ExpressionCommand expressionCommand);
    void visit(ConditionalCommand conditionalCommand);
    void visit(Color color);
    void visit(ColorCommand colorCommand);
    void visit(BinaryOperation binaryOperation);
    void visit(Variable variable);
    void visit(VariableType variableType);
    void visit(Assignment assignment);
    void visit(UnaryOperation unaryOperation);
    void visit(Number number);
    void visit(SetPosition setPosition);
    void visit(ChildrenNode childrenNode);
    void visit(IfElse ifElse);
    void visit(ProcedureCall procedureCall);
    void visit(ProcedureDeclaration procedureDeclaration);
    void visit(Parameter param);
}
