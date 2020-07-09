package SymbolTable;

import Dictionary.ErrorMessage;

import java.util.HashMap;

public class SymbolTableScope {
    private HashMap<String, Symbol> symbols;
    private int scopeLevel;
    private SymbolTableScope globalScope;
    public SymbolTableScope(int level, SymbolTableScope enclosingScope) {
        symbols = new HashMap<>();
        this.scopeLevel = level;
        this.globalScope = enclosingScope;
        init();
    }
    public void define(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }
    public Symbol find(String name) throws Exception {
        Symbol retrievedSymbol = symbols.get(name);
        if (retrievedSymbol != null)
            return retrievedSymbol;
        else
            if (globalScope != null && (retrievedSymbol = globalScope.symbols.get(name)) != null)
                return retrievedSymbol;
            else
                throw new Exception(ErrorMessage.SYMBOL_NOT_FOUND + name);
    }
    public boolean contains(String name) {
        return symbols.containsKey(name);
    }
    public int getScopeLevel() {
        return scopeLevel;
    }
    public SymbolTableScope getGlobalScope() {
        return globalScope;
    }
    private void init() {
        define(new BuiltinTypeSymbol("num"));
    }
}
