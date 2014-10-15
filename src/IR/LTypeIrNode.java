package IR;

public class LTypeIrNode extends IrNode{
    // It's just a label
    String label;

    public LTypeIrNode(Opcode opcode, String label) {
        super(opcode);
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + label;
    }

    @Override
    public String toAssembly() {
        return "label " + label;
    }
}
