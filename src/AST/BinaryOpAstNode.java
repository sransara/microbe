package AST;

import IR.*;
import SymbolScope.Symbol;
import SymbolScope.VariableType;

import java.util.List;

public class BinaryOpAstNode extends AstNode{
    public static enum OpType {
        ADD, MINUS, MULTIPLY, DIVIDE,
        ASSIGN,
        GT, LT, GTE, LTE, EQUALS, NEQUALS
    }

    public OpType op;
    public AstNode right;
    public AstNode left;

    public BinaryOpAstNode(OpType op, AstNode left,  AstNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode c = null;
        switch (op) {
            case ADD:
            case MINUS:
            case MULTIPLY:
            case DIVIDE: {
                c = generateRTypeIrCode();
                break;
            }
            case ASSIGN: {
                c = generateSTypeIrCode();
                break;
            }
        }
        return c;
    }

    private IrCode generateRTypeIrCode() {
        IrCode lC = left.generateIrCode();
        IrCode rC = right.generateIrCode();
        IrCode self = new IrCode();

        rC.rlvalue = IrCode.RLValue.RVALUE;
        lC.rlvalue = IrCode.RLValue.RVALUE;

        self.irNodeList.addAll(lC.irNodeList);
        self.irNodeList.addAll(rC.irNodeList);

        IrNode.Opcode opcode = IrNode.Opcode.UNKNWN;
        if(lC.type == VariableType.INT) {
            switch (op) {
                case ADD: {
                    opcode = IrNode.Opcode.ADDI;
                    break;
                }
                case MINUS: {
                    opcode = IrNode.Opcode.SUBI;
                    break;
                }
                case MULTIPLY: {
                    opcode = IrNode.Opcode.MULTI;
                    break;
                }
                case DIVIDE: {
                    opcode = IrNode.Opcode.DIVI;
                    break;
                }
            }
        }
        else if(lC.type == VariableType.FLOAT) {
            switch (op) {
                case ADD: {
                    opcode = IrNode.Opcode.ADDF;
                    break;
                }
                case MINUS: {
                    opcode = IrNode.Opcode.SUBF;
                    break;
                }
                case MULTIPLY: {
                    opcode = IrNode.Opcode.MULTF;
                    break;
                }
                case DIVIDE: {
                    opcode = IrNode.Opcode.DIVF;
                    break;
                }
            }
        }

        self.irNodeList.add(new RTypeIrNode(opcode, lC.result, rC.result, IrCodeState.newTemp()));
        self.result = IrCodeState.getTemp();
        self.type = lC.type;

        return self;
    }

    private IrCode generateSTypeIrCode() {
        IrCode rC = right.generateIrCode();
        IrCode lC = left.generateIrCode();
        rC.rlvalue = IrCode.RLValue.RVALUE;
        assert lC.rlvalue == IrCode.RLValue.LVALUE : "Assignment's LHS has a RVALUE";

        IrCode self = new IrCode();
        self.irNodeList.addAll(rC.irNodeList);

        if(lC.type == VariableType.INT) {
            if(rC.result.startsWith("$T")) {
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREI, rC.result, lC.result));
            }
            else {
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREI, rC.result, IrCodeState.newTemp()));
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREI, IrCodeState.getTemp(), lC.result));
            }
            self.type = lC.type;
        }
        else if(lC.type == VariableType.FLOAT) {
            if(rC.result.startsWith("$T")) {
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREF, rC.result, lC.result));
            }
            else {
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREF, rC.result, IrCodeState.newTemp()));
                self.irNodeList.add(new STypeIrNode(IrNode.Opcode.STOREF, IrCodeState.getTemp(), lC.result));
            }
            self.type = lC.type;
        }
        return self;
    }
}