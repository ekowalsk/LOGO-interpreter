package Stack;

import Dictionary.Constants;
import Dictionary.ErrorMessage;
import Nodes.ProcedureDeclaration;

import java.util.HashMap;

public class Stack {
    private HashMap<String, Variable>[] localVariables;
    private static HashMap<String, Variable> globalVariables;
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
    public void pushLocalVariable(String variableName, String type, Object value) {
        localVariables[level].put(variableName, new Variable(variableName, type, value));
    }
    public void pushGlobalVariable(String variableName, String type, Object value) {
        globalVariables.put(variableName, new Variable(variableName, type, value));
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
    public Variable getLocalVariable(String variableName, int level) throws Exception {
        Variable variable = localVariables[level].get(variableName);
        if (variable != null)
            return variable;
        else
            throw new Exception(ErrorMessage.VAR_NOT_FOUND + variableName);
    }
    public Variable getGlobalVariable(String variableName) throws Exception {
        Variable variable = globalVariables.get(variableName);
        if (variable != null)
            return variable;
        else
            throw new Exception(ErrorMessage.VAR_NOT_FOUND + variableName);
    }
    public void clearLocalVariables() {
        localVariables[level] = new HashMap<>();
    }
    public int getLevel() { return level; }
    private void initLocalVariables() {
        localVariables  = new HashMap[Constants.MAX_NEST_LEVEL];
        for (int i = 0; i < Constants.MAX_NEST_LEVEL; i++)
            localVariables[i] = new HashMap<>();
    }
}
