package Stack;

import Dictionary.Constants;
import Dictionary.ErrorMessage;
import Nodes.ProcedureDeclaration;
import SymbolTable.VariableSymbol;

import java.util.HashMap;

public class Stack {
    private HashMap<String, VariableSymbol>[] localVariables;
    private static HashMap<String, VariableSymbol> globalVariables;
    private static HashMap<String, ProcedureDeclaration> procedures;
    private int level;
    public Stack() {
        initLocalVariables();
        if (globalVariables == null)
            globalVariables = new HashMap<>();
        if (procedures == null)
            procedures = new HashMap<>();
        level = 0;
    }
    public boolean containsProcedure(String procedureName) {
        return procedures.containsKey(procedureName);
    }
    public boolean containsLocalVariable(String variableName, int level) {
        return localVariables[level].containsKey(variableName);
    }
    public boolean containsGlobalVariable(String variableName) {
        return globalVariables.containsKey(variableName);
    }
    public void pushLocalVariable(String variableName, VariableSymbol value) {
        localVariables[level].put(variableName, value);
    }
    public void pushGlobalVariable(String variableName, VariableSymbol value) {
        globalVariables.put(variableName, value);
    }
    public void pushProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        procedures.put(procedureDeclaration.getName(), procedureDeclaration);
    }
    public void increaseLevel() {
        level++;
    }
    public void decreaseLevel() {
        level--;
    }
    public ProcedureDeclaration getProcedure(String procedureName) throws Exception {
        ProcedureDeclaration procedureDeclaration = procedures.get(procedureName);
        if (procedureDeclaration != null)
            return procedureDeclaration;
        else
            throw new Exception(ErrorMessage.PROCEDURE_NOT_FOUND + procedureName);
    }
    public VariableSymbol getLocalVariable(String variableName, int level) throws Exception {
        VariableSymbol variable = localVariables[level].get(variableName);
        if (variable != null)
            return variable;
        else
            throw new Exception(ErrorMessage.VAR_NOT_FOUND + variableName);
    }
    public VariableSymbol getGlobalVariable(String variableName) throws Exception {
        VariableSymbol variable = globalVariables.get(variableName);
        if (variable != null)
            return variable;
        else
            throw new Exception(ErrorMessage.VAR_NOT_FOUND + variableName);
    }
    public void clearLocalVariables() {
        localVariables[level] = new HashMap<>();
    }
    private void initLocalVariables() {
        localVariables  = new HashMap[Constants.MAX_NEST_LEVEL];
        for (int i = 0; i < Constants.MAX_NEST_LEVEL; i++)
            localVariables[i] = new HashMap<>();
    }
}
