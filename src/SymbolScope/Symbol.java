package SymbolScope;

public class Symbol<T> {
    public VariableType type;
    public String name;
    T value;

    Symbol(VariableType t, String n) {
        this.type = t;
        this.name = n;
    }

    Symbol(VariableType t, String n, T v) {
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
                return "str " + name + " " + value;
            default:
                return "var " + name;
        }
    }
}


