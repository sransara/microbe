package IR;

public class RTypeIrNode extends IrNode{
    String op1;
    String op2;
    String result;

    public RTypeIrNode(Opcode opcode, String op1, String op2, String result) {
        super(opcode);
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1 + " " + op2 + " " + result;
    }

    @Override
    public String toAssembly() {
        StringBuilder a = new StringBuilder("move ");
        a.append(op1.replace("$T", "r") + " ");
        a.append(result.replace("$T", "r"));
        a.append("\n");
        switch(opcode) {
            case ADDF: a.append("addr "); break;
            case ADDI: a.append("addi "); break;
            case SUBF: a.append("subr "); break;
            case SUBI: a.append("subi "); break;
            case MULTF: a.append("mulr "); break;
            case MULTI: a.append("muli "); break;
            case DIVF: a.append("divr "); break;
            case DIVI: a.append("divi "); break;
        }
        a.append(op2.replace("$T", "r") + " ");
        a.append(result.replace("$T", "r"));
        return a.toString();
    }
}
