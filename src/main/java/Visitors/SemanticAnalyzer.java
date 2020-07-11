package Visitors;

import Dictionary.Constants;
import Dictionary.ErrorMessage;
import Nodes.*;
import Nodes.ChildrenNodes.ChildrenNode;
import Nodes.ColorCommands.ColorCommand;
import Nodes.ConditionalCommands.ConditionalCommand;
import Nodes.ExpressionCommands.ExpressionCommand;
import Nodes.Number;
import Nodes.SimpleCommands.SimpleCommand;
import SymbolTable.ProcedureSymbol;
import SymbolTable.Symbol;
import SymbolTable.SymbolTableScope;
import SymbolTable.VariableSymbol;

import java.util.ListIterator;

public class SemanticAnalyzer implements Visitor {
    private static SymbolTableScope scope;
    public SemanticAnalyzer() {
        if (scope == null)
            scope = new SymbolTableScope(0, null);
    }
    public void visit(Assignment assignment) throws Exception {
        if (!scope.contains(assignment.getVar().getName()))
            declareSymbol(assignment.getVar(), assignment.getType());
        assignment.getExpression().accept(this);
    }
    public void visit(BinaryOperation binaryOperation) throws Exception {
        binaryOperation.getLeftExpression().accept(this);
        binaryOperation.getRightExpression().accept(this);
    }
    public void visit(SimpleCommand simpleCommand) {}
    public void visit(ChildrenNode childrenNode) throws Exception {
        ListIterator children = childrenNode.getChildren();
        while (children.hasNext())
            ((Node) children.next()).accept(this);
    }
    public void visit(Color color){}
    public void visit(ExpressionCommand expressionCommand) throws Exception {
        expressionCommand.getExpression().accept(this);
    }
    public void visit(IfElse ifElse) throws Exception {
        ifElse.getCondition().accept(this);
        ifElse.getTrueBlock().accept(this);
        if(ifElse.getFalseBlock() != null) {
            ifElse.getFalseBlock().accept(this);
        }
    }
    public void visit(Number number) {}
    public void visit(Parameter parameter) {}
    public void visit(ProcedureCall procedureCall) {}
    public void visit(ProcedureDeclaration procedureDeclaration) throws Exception {
        if(scope.getScopeLevel() == Constants.MAX_NEST_LEVEL)
            throw new Exception(ErrorMessage.MAX_NEST_LEXEL_EXCEED);
        ProcedureSymbol procedureSymbol = new ProcedureSymbol(procedureDeclaration.getName(), null);
        scope.define(procedureSymbol);
        scope = new SymbolTableScope(scope.getScopeLevel() + 1, scope);
        ListIterator parameters = procedureDeclaration.getParameters();
        while (parameters.hasNext()) {
            VariableSymbol parameterSymbol = createParameterSymbol((Parameter) parameters.next());
            scope.define(parameterSymbol);
            procedureSymbol.addParameter(parameterSymbol);
        }
        ListIterator children = procedureDeclaration.getChildren();
        while (children.hasNext())
            ((Node) children.next()).accept(this);
        scope = scope.getGlobalScope();
    }
    public void visit(ConditionalCommand conditionalCommand) throws Exception {
        conditionalCommand.getExpression().accept(this);
        conditionalCommand.getBlock().accept(this);
    }
    public void visit(ColorCommand colorCommand) {}
    public void visit(SetPosition setPosition) throws Exception {
        setPosition.getExpressionX().accept(this);
        setPosition.getExpressionY().accept(this);
    }
    public void visit(Strings strings) {}
    public void visit(VariableType variableType) {}
    public void visit(UnaryOperation unaryOperation) throws Exception {
        unaryOperation.getExpression().accept(this);
    }
    public void visit(Var var) throws Exception {
        scope.find(var.getName());
    }
    private VariableSymbol createParameterSymbol(Parameter parameter) throws Exception {
        Symbol parameterType = scope.find(parameter.getType().getName());
        return new VariableSymbol(parameter.getVar().getName(), parameterType.getName());
    }
    private void declareSymbol(Var var, VariableType type) throws Exception {
        Symbol typeSymbol = scope.find(type.getName());
        VariableSymbol variableSymbol = new VariableSymbol(var.getName(), typeSymbol.getName());
        scope.define(variableSymbol);
    }
}