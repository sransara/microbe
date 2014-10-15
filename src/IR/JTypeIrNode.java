package IR;

public class JTypeIrNode extends IrNode{
    String label;

    public JTypeIrNode(Opcode opcode, String label) {
        super(opcode);
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + label;
    }
}
