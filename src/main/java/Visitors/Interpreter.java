package Visitors;

import Dictionary.ErrorMessage;
import Nodes.*;
import Nodes.ChildrenNodes.ChildrenNode;
import Nodes.ColorCommands.ColorCommand;
import Nodes.ColorCommands.SetPenColor;
import Nodes.ConditionalCommands.ConditionalCommand;
import Nodes.ExpressionCommands.*;
import Nodes.Number;
import Nodes.SimpleCommands.*;
import Parser.Parser;
import Stack.Stack;
import Turtle.Turtle;

import java.util.ListIterator;

import static Turtle.Turtle.bgcolor;

public class Interpreter implements Visitor {
    private Parser parser;
    private Stack stack;
    private static Turtle turtle;
    public Interpreter(Parser parser) {
        this.parser = parser;
        this.stack = new Stack();
        if (turtle == null) {
            turtle = new Turtle();
            turtle.left(90);
        }
    }
    public static void main (String[] args) {
        bgcolor("lightblue");
        turtle.penColor("black");
        turtle.width(1);
    }
    public void visit(Assignment assignment) throws Exception {
        String variableName = assignment.getVariable().getName();
        assignment.getExpression().accept(this);
        stack.pushGlobalVariable(variableName, assignment.getExpression().getOutput());
    }
    public void visit(BinaryOperation binaryOperation) throws Exception {
        binaryOperation.getLeftExpression().accept(this);
        binaryOperation.getRightExpression().accept(this);
        Double leftExpression = Double.parseDouble(binaryOperation.getLeftExpression().getOutput().toString());
        Double rightExpression = Double.parseDouble(binaryOperation.getRightExpression().getOutput().toString());
        switch(binaryOperation.getOperator()) {
            case "+":
                binaryOperation.setOutput(leftExpression + rightExpression);
                break;
            case "-":
                binaryOperation.setOutput(leftExpression - rightExpression);
                break;
            case "*":
                binaryOperation.setOutput(leftExpression * rightExpression);
                break;
            case "/":
                binaryOperation.setOutput(leftExpression / rightExpression);
                break;
            case "<":
                binaryOperation.setOutput(leftExpression < rightExpression);
                break;
            case ">":
                binaryOperation.setOutput(leftExpression > rightExpression);
                break;
            case ">=":
                binaryOperation.setOutput(leftExpression >= rightExpression);
                break;
            case "<=":
                binaryOperation.setOutput(leftExpression <= rightExpression);
                break;
            case "<>":
                binaryOperation.setOutput(!leftExpression.equals(rightExpression));
                break;
            case "=":
                binaryOperation.setOutput(leftExpression.equals(rightExpression));
                break;
            default:
                throw new Exception(ErrorMessage.WRONG_OPERATOR + binaryOperation.getOperator());
        }
    }
    public void visit(ExpressionCommand expressionCommand) throws Exception {
        expressionCommand.getExpression().accept(this);
        Double output = Double.parseDouble(expressionCommand.getExpression().getOutput().toString());
        if (expressionCommand instanceof Backward)
            turtle.forward(-output);
        else if (expressionCommand instanceof Forward)
            turtle.forward(output);
        else if (expressionCommand instanceof Left)
            turtle.left(output);
        else if (expressionCommand instanceof Right)
            turtle.right(output);
    }
    public void visit(SimpleCommand simpleCommand) throws Exception {
        if (simpleCommand instanceof Clean)
            turtle.clear();
        else if (simpleCommand instanceof Down)
            turtle.down();
        else if (simpleCommand instanceof Hide)
            turtle.hide();
        else if (simpleCommand instanceof Show)
            turtle.show();
        else if (simpleCommand instanceof Up)
            turtle.up();
    };
    public void visit(ConditionalCommand conditionalCommand) throws Exception {

    };
    public void visit(Color color) {
        color.setOutput(color.getName());
    };
    public void visit(ColorCommand colorCommand) {
        colorCommand.getColor().accept(this);
        if (colorCommand instanceof SetPenColor)
            turtle.penColor(colorCommand.getColor().getOutput().toString());
        else
            turtle.fillColor(colorCommand.getColor().getOutput().toString());
    };
    public void visit(Variable variable) throws Exception {};
    public void visit(VariableType variableType) {};
    public void visit(UnaryOperation unaryOperation) throws Exception {};
    public void visit(Number number) {
        number.setOutput(Double.parseDouble(number.getValue()));
    };
    public void visit(SetPosition setPosition) throws Exception {
        setPosition.getExpressionX().accept(this);
        setPosition.getExpressionY().accept(this);
        Double x = Double.parseDouble(setPosition.getExpressionX().getOutput().toString());
        Double y = Double.parseDouble(setPosition.getExpressionY().getOutput().toString());
        turtle.setPosition(x, y);
    };
    public void visit(ChildrenNode childrenNode)throws Exception {
        ListIterator children = childrenNode.getChildren();
        while (children.hasNext())
            ((Node) children.next()).accept(this);
    };
    public void visit(IfElse ifElse) throws Exception {};
    public void visit(ProcedureCall procedureCall) {};
    public void visit(ProcedureDeclaration procedureDeclaration) throws Exception {};
    public void visit(Parameter param) {};
    public void visit(Strings strings) {};
}
