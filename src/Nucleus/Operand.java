package Nucleus;

public class Operand {
    public static enum OperandType {
        GLOBAL_SYMBOL, SYMBOL,
        TEMPORARY, SPILLED_TEMPORARY,
        LITERAL
    }

    public static enum DataType {
        STRING, INT, FLOAT, VOID
    }

    public OperandType operandType;
    public DataType dataType;
    public String reference;
    public Object value;

    public String toString() {
        return reference;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) { return false; }
        return ((Operand)o).reference.equals(reference);
    }

    public boolean isRegisterable() {
        return operandType != Operand.OperandType.LITERAL && dataType != Operand.DataType.STRING;
    }
}
