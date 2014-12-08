package Nucleus;

public class Literal extends Operand {
    public Literal(DataType t, Object v, OperandType o) {
        dataType = t;
        reference = v.toString();
        value = v;
        operandType = o;
    }
}
