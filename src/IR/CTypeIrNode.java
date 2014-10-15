package IR;

public class CTypeIrNode extends IrNode{
    // Conditionals

    String op1;
    String op2;
    String label;

    public CTypeIrNode(Opcode opcode, String op1, String op2, String label) {
        super(opcode);
        this.op1 = op1;
        this.op2 = op2;
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1 + " " + op2 + " " + label;
    }
}
