package Nucleus;

public class Operand {
    public static enum OperandType {
        SYMBOL, TEMPORARY, LITERAL
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
}
