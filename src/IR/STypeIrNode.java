package IR;

public class STypeIrNode extends IrNode{
    // STOREI, STOREF

    String op1;
    String result;

    public STypeIrNode(Opcode opcode, String op1, String result) {
        super(opcode);
        this.op1 = op1;
        this.result = result;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1 + " " + result;
    }

    @Override
    public String toAssembly() {
        StringBuilder a = new StringBuilder("move ");
        a.append(ttoreg(op1) + " ");
        a.append(ttoreg(result) + " ");
        return a.toString();
    }
}
