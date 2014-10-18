package AST;

import IR.*;
import SymbolScope.VariableType;

public class ConditionOpAstNode extends AstNode{
    public static enum OpType {
        GT, LT, GE, LE, EQ, NE
    }

    public OpType op;
    public AstNode right;
    public AstNode left;
    public String jump_label;

    public ConditionOpAstNode(OpType op, AstNode left, AstNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode lC = left.generateIrCode();
        IrCode rC = right.generateIrCode();
        IrCode self = new IrCode();

        rC.rlvalue = IrCode.RLValue.RVALUE;
        lC.rlvalue = IrCode.RLValue.RVALUE;

        self.irNodeList.addAll(lC.irNodeList);
        self.irNodeList.addAll(rC.irNodeList);

        IrNode.Opcode opcode = IrNode.Opcode.UNKNWN;
        IrNode.Opcode st_opcode = IrNode.Opcode.UNKNWN;

        if(lC.type == VariableType.INT) {
            self.type = VariableType.INT;
            st_opcode = IrNode.Opcode.STOREI;

        }
        else if(lC.type == VariableType.FLOAT) {
            self.type = VariableType.FLOAT;
            st_opcode = IrNode.Opcode.STOREF;
        }

        switch (op) {
            case GT: {
                opcode = IrNode.Opcode.GT;
                break;
            }
            case LT: {
                opcode = IrNode.Opcode.LT;
                break;
            }
            case GE: {
                opcode = IrNode.Opcode.GE;
                break;
            }
            case LE: {
                opcode = IrNode.Opcode.LE;
                break;
            }
            case NE: {
                opcode = IrNode.Opcode.NE;
                break;
            }
            case EQ: {
                opcode = IrNode.Opcode.EQ;
                break;
            }
        }
        assert self.type != VariableType.VOID : "Type is not defined for the condition";

        // Right child needs to be a temp / register
        if(rC.isTempResult()) {
            self.irNodeList.add(new CTypeIrNode(opcode, lC.result, rC.result, this.jump_label, self.type));
        }
        else {
            self.irNodeList.add(new STypeIrNode(st_opcode, rC.result, IrCodeState.newTemp()));
            self.irNodeList.add(new CTypeIrNode(opcode, lC.result, IrCodeState.getTemp(), this.jump_label, self.type));
        }
        return self;
    }
}
