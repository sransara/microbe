package IR;

public class JTypeIrNode extends IrNode{
    String label;

    public JTypeIrNode(Opcode opcode, String label) {
        // Kind of dump to do it this way because there's only one IR Opcode for JUMP
        // but let's stick with it for easy extension and consistency
        super(opcode);
        this.label = label;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + label;
    }

    @Override
    public String toTiny() {
        String r = null;
        switch (opcode) {
            case JUMP:
                r = "jmp " + label;
                break;
            case JSR:
                r = "jsr " + label;
                break;
        }
        return r;
    }
}
