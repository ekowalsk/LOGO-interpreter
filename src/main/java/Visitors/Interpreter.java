package Visitors;

import Dictionary.Constants;
import Dictionary.ErrorMessage;
import Nodes.*;
import Nodes.ChildrenNodes.ChildrenNode;
import Nodes.ColorCommands.ColorCommand;
import Nodes.ColorCommands.SetPenColor;
import Nodes.ConditionalCommands.ConditionalCommand;
import Nodes.ConditionalCommands.While;
import Nodes.ExpressionCommands.*;
import Nodes.Number;
import Nodes.SimpleCommands.*;
import Parser.Parser;
import Stack.Stack;
import Stack.Variable;
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
        String variableName = assignment.getVar().getName();
        assignment.getExpression().accept(this);
        stack.pushGlobalVariable(variableName, assignment.getType().getName(), assignment.getExpression().getOutput());
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
    }
    public void visit(ConditionalCommand conditionalCommand) throws Exception {
        conditionalCommand.getExpression().accept(this);
        if (conditionalCommand instanceof While) {
            while ((Boolean) conditionalCommand.getExpression().getOutput())
                conditionalCommand.getBlock().accept(this);
        }
        else {
            double doubleOutput = Double.parseDouble(conditionalCommand.getExpression().getOutput().toString());
            if (doubleOutput < 0 || (doubleOutput != Math.floor(doubleOutput)))
                throw new Exception(ErrorMessage.WRONG_EXPRESSION_REPEAT);
            for (int i = 0; i < (int) doubleOutput; i++)
                conditionalCommand.getBlock().accept(this);
        }
    }
    public void visit(Color color) {
        color.setOutput(color.getName());
    }
    public void visit(ColorCommand colorCommand) {
        colorCommand.getColor().accept(this);
        if (colorCommand instanceof SetPenColor)
            turtle.penColor(colorCommand.getColor().getOutput().toString());
        else
            turtle.fillColor(colorCommand.getColor().getOutput().toString());
    }
    public void visit(Var var) throws Exception {
        if (stack.containsLocalVariable(var.getName(), stack.getLevel()))
            setVarOutput(var, stack.getLocalVariable(var.getName(), stack.getLevel()));
        else if (stack.getLevel() != 0 && stack.containsLocalVariable(var.getName(), stack.getLevel() - 1))
            setVarOutput(var, stack.getLocalVariable(var.getName(), stack.getLevel() - 1));
        else if (stack.containsGlobalVariable(var.getName()))
            setVarOutput(var, stack.getGlobalVariable(var.getName()));
        else
            throw new Exception(ErrorMessage.VAR_NOT_FOUND + var.getName());
    }
    private void setVarOutput(Var varNode, Variable variable) {
        if (variable.getType() == Variable.Type.NUMBER)
            varNode.setOutput(Double.parseDouble(variable.getValue().toString()));
        else
            varNode.setOutput(variable.getValue());
    }
    public void visit(VariableType variableType) {};
    public void visit(UnaryOperation unaryOperation) throws Exception {
        String operator = unaryOperation.getOperator();
        unaryOperation.getExpression().accept(this);
        if (operator.equals("-"))
            unaryOperation.setOutput(-Double.parseDouble(unaryOperation.getExpression().getOutput().toString()));
        else
            unaryOperation.setOutput(Double.parseDouble(unaryOperation.getExpression().getOutput().toString()));
    }
    public void visit(Number number) {
        number.setOutput(Double.parseDouble(number.getValue()));
    }
    public void visit(SetPosition setPosition) throws Exception {
        setPosition.getExpressionX().accept(this);
        setPosition.getExpressionY().accept(this);
        double x = Double.parseDouble(setPosition.getExpressionX().getOutput().toString());
        double y = Double.parseDouble(setPosition.getExpressionY().getOutput().toString());
        turtle.setPosition(x, y);
    };
    public void visit(ChildrenNode childrenNode) throws Exception {
        ListIterator children = childrenNode.getChildren();
        while (children.hasNext())
            ((Node) children.next()).accept(this);
    };
    public void visit(IfElse ifElse) throws Exception {
        ifElse.getCondition().accept(this);
        if ((Boolean) ifElse.getCondition().getOutput())
            ifElse.getTrueBlock().accept(this);
        else
            if (ifElse.getFalseBlock() != null)
                ifElse.getFalseBlock().accept(this);
    }
    public void visit(ProcedureCall procedureCall) throws Exception {
        if (stack.getLevel() >= Constants.MAX_NEST_LEVEL)
            throw new Exception(ErrorMessage.MAX_NEST_LEXEL_EXCEED);
        if (!stack.containsProcedure(procedureCall.getName()))
            throw new Exception(ErrorMessage.PROCEDURE_NOT_FOUND + procedureCall.getName());
        ProcedureDeclaration procedureDeclaration = stack.getProcedure(procedureCall.getName());
        if (procedureCall.argumentsCount() != procedureDeclaration.parametersCount())
            throw new Exception(ErrorMessage.ARGUMENTS_NO_MISMATCH + procedureCall.getName());
        stack.increaseLevel();
        pushParameters(procedureDeclaration, procedureCall);
        procedureCall.setOutput(procedureDeclaration);
        ListIterator<Node> children = ((ProcedureDeclaration) procedureCall.getOutput()).getChildren();
        while (children.hasNext())
            children.next().accept(this);
        stack.clearLocalVariables();
        stack.decreaseLevel();
    }
    private void pushParameters(ProcedureDeclaration declaration, ProcedureCall call) throws Exception {
        for (int i = 0; i < declaration.parametersCount(); i++) {
            call.getArgument(i).accept(this);
            stack.pushLocalVariable(declaration.getParameter(i).getVar().getName(),
                    declaration.getParameter(i).getType().getName(), call.getArgument(i).getOutput());
        }
    }
    public void visit(ProcedureDeclaration procedureDeclaration) throws Exception {
        stack.pushProcedureDeclaration(procedureDeclaration);
    }
    public void visit(Parameter param) {};
    public void visit(Strings strings) {
        strings.setOutput(strings.getValue());
    }
}
