package AST;

import IR.*;
import Nucleus.Operand;
import SymbolScope.ScopeNode;

public class BinaryOpAstNode extends AstNode{
    public static enum OpType {
        ADD, MINUS, MULTIPLY, DIVIDE,
        ASSIGN,
        GT, LT, GE, LE, EQ, NE
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
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode c = null;
        switch (op) {
            case ADD:
            case MINUS:
            case MULTIPLY:
            case DIVIDE: {
                c = generateRTypeIrCode(scope);
                break;
            }
            case ASSIGN: {
                c = generateSTypeIrCode(scope);
                break;
            }
        }
        return c;
    }

    private IrCode generateRTypeIrCode(ScopeNode scope) {
        IrCode lC = left.generateIrCode(scope);
        IrCode rC = right.generateIrCode(scope);
        IrCode self = new IrCode();
        Operand.DataType resultDataType = lC.result.dataType;

        rC.rlvalue = IrCode.RLValue.RVALUE;
        lC.rlvalue = IrCode.RLValue.RVALUE;

        self.irNodeList.addAll(lC.irNodeList);
        self.irNodeList.addAll(rC.irNodeList);

        IrNode.Opcode opcode = IrNode.Opcode.UNKNWN;
        if(resultDataType == Operand.DataType.INT) {
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
        else if(resultDataType == Operand.DataType.FLOAT) {
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

        self.irNodeList.add(new RTypeIrNode(opcode, lC.result, rC.result, scope.createTemp(resultDataType)));
        self.result = scope.getCurrentTemp();

        return self;
    }

    private IrCode generateSTypeIrCode(ScopeNode scope) {
        IrCode rC = right.generateIrCode(scope);
        IrCode lC = left.generateIrCode(scope);
        Operand.DataType resultDataType = lC.result.dataType;
        rC.rlvalue = IrCode.RLValue.RVALUE;
        assert lC.rlvalue == IrCode.RLValue.LVALUE : "Assignment's LHS has a RVALUE";

        IrCode self = new IrCode();
        self.irNodeList.addAll(rC.irNodeList);

        if(resultDataType == Operand.DataType.INT) {
            if(rC.result.operandType == Operand.OperandType.TEMPORARY) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, rC.result, lC.result));
            }
            else {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, rC.result, scope.createTemp(resultDataType)));
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, scope.getCurrentTemp(), lC.result));
            }
        }
        else if(resultDataType == Operand.DataType.FLOAT) {
            if(rC.result.operandType == Operand.OperandType.TEMPORARY) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, rC.result, lC.result));
            }
            else {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, rC.result, scope.createTemp(resultDataType)));
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, scope.getCurrentTemp(), lC.result));
            }
        }
        return self;
    }
}
