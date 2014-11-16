package IR;

import Nucleus.Operand;

public class STypeIrNode extends IrNode{
    // the stack stuff
    public Operand operand;
    public int frameSize;

    public STypeIrNode(Opcode opcode, Operand operand, int frameSize) {
        super(opcode);
        this.operand = operand;
        this.frameSize = frameSize;
    }

    @Override
    public String toString() {
        String c = null;
        switch (opcode) {
            case LINK:
                c = "; LINK";
                break;
            case RET:
                c = "; RET";
                break;
            case PUSH:
            case POP:// PUSH POP
                c = "; " + opcode.name() + " ";
                if(operand != null) {
                    c += operand.reference;
                }
                break;
        }
        return c;
    }

    @Override
    public String toTiny() {
        String c = null;
        switch (opcode){
            case LINK:
                c = "link " + frameSize;
                break;
            case RET:
                c  = "unlnk \n";
                c += "ret";
                break;
            case PUSH:
            case POP:// PUSH POP
                c = opcode.name().toLowerCase() + " ";
                if(operand != null) {
                    c += operandToTiny(operand);
                }
                break;
         }
        return c;
    }
}
