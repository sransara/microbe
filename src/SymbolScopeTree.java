import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

enum ScopeType {
    GLOBAL, BLOCK, FUNCTION
}

enum VarType {
    STRING, INT, FLOAT, VOID
}

public class SymbolScopeTree {
    class Symbol<T> {
        VarType type;
        String name;
        T value;

        Symbol(VarType t, String n) {
            this.type = t;
            this.name = n;
        }

        Symbol(VarType t, String n, T v) {
            this.type = t;
            this.name = n;
            this.value = v;
        }

        void setValue(T value) {
            this.value = value;
        }

        T getValue() {
            return value;
        }

        @Override
        public String toString() {
            switch (type) {
                case STRING:
                    return "name " + name + " type " + type + " value " + value;
                default:
                    return "name " + name + " type " + type;
            }
        }
    }

    class SymbolScopeNode {
        ScopeType scopeType;
        SymbolScopeNode parent;
        VarType returnType = null;
        String name = null;
        Map<String, SymbolScopeNode> children = new LinkedHashMap<String, SymbolScopeNode>();
        Map<String, Symbol> symbolTable =  new LinkedHashMap<String, Symbol>();

        SymbolScopeNode(ScopeType scopeType) {
            this.scopeType = scopeType;
            this.parent = null;
        }

        SymbolScopeNode(ScopeType scopeType, SymbolScopeNode parent) {
            this.scopeType = scopeType;
            this.parent = parent;
        }

        SymbolScopeNode(ScopeType scopeType, SymbolScopeNode parent, VarType rt, String name) {
            this.scopeType = scopeType;
            this.parent = parent;
            this.returnType = rt;
            this.name = name;
        }

        private void addSymbol(VarType type, String name, String value) {
            switch (type) {
                case INT: {
                    Symbol<Integer> s = new Symbol<Integer>(type, name, 0);
                    symbolTable.put(name, s);
                    break;
                }
                case FLOAT: {
                    Symbol<Float> s = new Symbol<Float>(type, name, 0.0f);
                    symbolTable.put(name, s);
                    break;
                }
                case STRING: {
                    Symbol<String> s = new Symbol<String>(type, name, value);
                    symbolTable.put(name, s);
                }
            }
        }

        public void addVariables(String type, List<String> names) {
            VarType t = VarType.valueOf(type);
            for (String name : names) {
                addVariable(t, name);
            }
        }

        public void addVariable(VarType t, String name) {
            if(symbolTable.containsKey(name)) {
                System.out.println("DECLARATION ERROR " + name);
                System.exit(0);
            }
            addSymbol(t, name, null);
        }

        public void addVariable(String type, String name) {
            if(symbolTable.containsKey(name)) {
                System.out.println("DECLARATION ERROR " + name);
                System.exit(0);
            }
            VarType t = VarType.valueOf(type);
            addSymbol(t, name, null);
        }

        public void addVariable(String type, String name, String value) {
            if(symbolTable.containsKey(name)) {
                System.out.println("DECLARATION ERROR " + name);
                System.exit(0);
            }
            VarType t = VarType.valueOf(type);
            addSymbol(t, name, value);
        }

        public void printSymbols() {
            Set<String> symbolNames = symbolTable.keySet();
            for (String symbolName : symbolNames) {
                System.out.println(symbolTable.get(symbolName));
            }
            System.out.println("");
        }

    }

    public SymbolScopeNode GlobalScope = new SymbolScopeNode(ScopeType.GLOBAL);
    public SymbolScopeNode currentScope;
    private int blockCount = 1;

    public SymbolScopeTree() {
        currentScope = GlobalScope;
    }

    public void exitScope() {
        currentScope = currentScope.parent;
    }

    public void enterScope(String rt, String name) {
        VarType returnType = VarType.valueOf(rt);
        SymbolScopeNode ns = new SymbolScopeNode(ScopeType.FUNCTION, currentScope, returnType, name);
        currentScope.children.put(name, ns);
        currentScope = ns;
    }

    public void enterScope() {
        SymbolScopeNode ns = new SymbolScopeNode(ScopeType.BLOCK, currentScope);
        String name = "BLOCK " + blockCount; blockCount += 1;
        currentScope.children.put(name, ns);
        currentScope = ns;
    }

    public void printTree(String scopeName, SymbolScopeNode cs) {
        System.out.println("Symbol table " + scopeName);
        cs.printSymbols();
        Set<String> scopeNames = cs.children.keySet();
        for(String sn : scopeNames) {
            printTree(sn, cs.children.get(sn));
        }
    }
}
