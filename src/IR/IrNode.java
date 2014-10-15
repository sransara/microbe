package IR;

public abstract class IrNode {
    public static enum Opcode {
        ADDI, ADDF,
        SUBI, SUBF,
        MULTI, MULTF,
        DIVI, DIVF,
        STOREI, STOREF,
        GT, GE, LT, LE, NE, EQ,
        JUMP,
        LABEL,
        READI, READF, READS, WRITEI, WRITEF, WRITES,
        RET,
        UNKNWN
    }

    public Opcode opcode;

    public IrNode(Opcode opcode) {
        this.opcode = opcode;
    }

    public abstract String toString();

    public String toAssembly() { return "--todo as conversion--"; }
}
