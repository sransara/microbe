package IR;

import SymbolScope.VariableType;

public class CTypeIrNode extends IrNode{
    // Conditionals

    String op1;
    String op2;
    String label;
    VariableType type;

    public CTypeIrNode(Opcode opcode, String op1, String op2, String label, VariableType type) {
        super(opcode);
        this.op1 = op1;
        this.op2 = op2;
        this.label = label;
        this.type = type;
    }

    @Override
    public String toString() {
        return "; " + opcode.name() + " " + op1 + " " + op2 + " " + label;
    }

    @Override
    public String toAssembly() {
        StringBuilder a = new StringBuilder();
        if(type == VariableType.INT) {
            a.append("cmpi " + ttoreg(op1) + " " + ttoreg(op2));
        }
        else if (type == VariableType.FLOAT) {
            a.append("cmpr " + ttoreg(op1) + " " + ttoreg(op2));
        }
        a.append("\n");

        switch(opcode) {
            case GT: a.append("jgt "); break;
            case LT: a.append("jlt "); break;
            case GE: a.append("jge "); break;
            case LE: a.append("jle "); break;
            case NE: a.append("jne "); break;
            case EQ: a.append("jeq "); break;
        }
        a.append(label);

        return a.toString();
    }
}
