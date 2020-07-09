package SymbolTable;

import java.util.LinkedList;

public class ProcedureSymbol extends Symbol {
    private LinkedList<VariableSymbol> parameters;
    public ProcedureSymbol(String name, LinkedList<VariableSymbol> parameters) {
        super(name);
        this.parameters = (parameters == null) ? new LinkedList<>() : parameters;
    }
    public void addParameter(VariableSymbol variableSymbol) {
        this.parameters.add(variableSymbol);
    }
}
