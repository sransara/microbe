package IR;

public class ITypeIrNode extends IrNode{
    // System Interrupt
    String result;

    public ITypeIrNode(Opcode opcode, String result) {
        super(opcode);
        this.result = result;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + result;
    }

    @Override
    public String toAssembly() {
        StringBuilder a = new StringBuilder("sys ");
        switch(opcode) {
            case WRITEF: a.append("writer "); break;
            case WRITEI: a.append("writei "); break;
            case WRITES: a.append("writes "); break;
            case READF: a.append("readr "); break;
            case READI: a.append("readi "); break;
            case READS: a.append("reads "); break;
        }
        a.append(ttoreg(result));
        return a.toString();
    }
}
