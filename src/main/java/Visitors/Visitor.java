package Visitors;

import Nodes.*;
import Nodes.ChildrenNodes.ChildrenNode;
import Nodes.ColorCommands.ColorCommand;
import Nodes.ConditionalCommands.ConditionalCommand;
import Nodes.ExpressionCommands.ExpressionCommand;
import Nodes.SimpleCommands.SimpleCommand;
import Nodes.Number;

public interface Visitor {
    void visit(SimpleCommand simpleCommand) throws Exception;
    void visit(ExpressionCommand expressionCommand) throws Exception;
    void visit(ConditionalCommand conditionalCommand) throws Exception;
    void visit(Color color);
    void visit(ColorCommand colorCommand);
    void visit(BinaryOperation binaryOperation) throws Exception;
    void visit(Var var) throws Exception;
    void visit(VariableType variableType);
    void visit(Assignment assignment) throws Exception;
    void visit(UnaryOperation unaryOperation) throws Exception;
    void visit(Number number);
    void visit(SetPosition setPosition) throws Exception;
    void visit(ChildrenNode childrenNode)throws Exception;
    void visit(IfElse ifElse) throws Exception;
    void visit(ProcedureCall procedureCall) throws Exception;
    void visit(ProcedureDeclaration procedureDeclaration) throws Exception;
    void visit(Parameter param);
    void visit(Strings strings);
}
