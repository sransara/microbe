package Nucleus;

public class Literal extends Operand {
    public Literal(DataType t, Object v) {
        dataType = t;
        reference = v.toString();
        value = v;
    }
}
