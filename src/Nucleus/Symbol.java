package Nucleus;

public class Symbol extends Operand {
    public Symbol(DataType t, String n, Object v, OperandType o) {
        dataType = t;
        reference = n;
        value = v;
        operandType = o;
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


