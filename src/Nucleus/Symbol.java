package Nucleus;

public class Symbol extends Operand {
    public Symbol(DataType t, String n) {
        dataType = t;
        reference = n;
    }

    public Symbol(DataType t, String n, Object v) {
        dataType = t;
        reference = n;
        value = v;
    }

    @Override
    public String toString() {
        switch (dataType) {
            case STRING:
                return "str " + reference + " " + value;
            default:
                return "var " + reference;
        }
    }
}


