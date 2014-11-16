package Nucleus;

public class Temporary extends Operand {
    public Temporary(DataType t, int temp_x) {
        operandType = OperandType.TEMPORARY;
        dataType = t;
        reference = "$T" + temp_x;
    }
}
