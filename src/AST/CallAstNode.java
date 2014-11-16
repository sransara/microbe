package AST;

import IR.*;
import Nucleus.Operand;
import Nucleus.Symbol;
import SymbolScope.FunctionScopeNode;
import SymbolScope.ScopeNode;
import SymbolScope.SymbolScopeTree;

import javax.xml.crypto.Data;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CallAstNode extends AstNode{
    public static enum OpType {
        RET, CALL
    }
    OpType op;
    String function;
    LinkedList<AstNode> exprs;
    AstNode returnExpr;

    public CallAstNode(OpType op, String function, LinkedList<AstNode> exprs) {
        this.op = op;
        this.function = function;
        this.exprs = exprs;
    }

    public CallAstNode(OpType op, AstNode returnExpr) {
        this.op = op;
        this.returnExpr = returnExpr;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode c = null;
        switch (op) {
            case RET: c = generateRetIrCode(scope); break;
            case CALL: c = generateCallIrCode(scope); break;
        }
        return c;
    }

    private IrCode generateRetIrCode(ScopeNode scope) {
        IrCode self = new IrCode();
        IrCode r = returnExpr.generateIrCode(scope);
        self.irNodeList.addAll(r.irNodeList);
        Operand.DataType resultDataType = r.result.dataType;
        String rref = "$R" + scope.getParentFunction().parameter_x;
        Symbol dr = new Symbol(resultDataType, rref, null);
        if(r.result.operandType == Operand.OperandType.TEMPORARY) {
            if (resultDataType == Operand.DataType.INT) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, r.result, dr));
            } else if (resultDataType == Operand.DataType.FLOAT) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, r.result, dr));
            }
        }
        else {
            if (resultDataType == Operand.DataType.INT) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, r.result, scope.createTemp(resultDataType)));
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREI, scope.getCurrentTemp(), dr));
            } else if (resultDataType == Operand.DataType.FLOAT) {
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, r.result, scope.createTemp(resultDataType)));
                self.irNodeList.add(new MTypeIrNode(IrNode.Opcode.STOREF, scope.getCurrentTemp(), dr));
            }
        }
        self.irNodeList.add(new STypeIrNode(IrNode.Opcode.RET, null, 0));
        return self;
    }

    private IrCode generateCallIrCode(ScopeNode scope) {
        IrCode self = new IrCode();
        // saved registers
        // result
        // arg n
        // arg n-1
        // arg 1
        // ret address
        // prev stack pointer <- bp
        self.irNodeList.add(new STypeIrNode(IrNode.Opcode.PUSH, null, 0));
        List<Operand> tempResults = new LinkedList<Operand>();

        Iterator<AstNode> iexprs = exprs.descendingIterator();
        while (iexprs.hasNext()) {
            // Build code right to left
            AstNode n = iexprs.next();
            IrCode nc = n.generateIrCode(scope);
            self.irNodeList.addAll(nc.irNodeList);
            tempResults.add(nc.result);
        }

        for(Operand t : tempResults) {
            self.irNodeList.add(new STypeIrNode(IrNode.Opcode.PUSH, t, 0));
        }
        self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JSR, function));
        for(Operand t : tempResults) {
            self.irNodeList.add(new STypeIrNode(IrNode.Opcode.POP, null, 0));
        }

        FunctionScopeNode f = (FunctionScopeNode)SymbolScopeTree.GlobalScope.children.get(function);
        Operand.DataType returnType = f.returnType;

        if(returnType != Operand.DataType.VOID){
            self.irNodeList.add(new STypeIrNode(IrNode.Opcode.POP, scope.createTemp(returnType), 0));
            self.result = scope.getCurrentTemp();
        }
        else {
            self.irNodeList.add(new STypeIrNode(IrNode.Opcode.POP, null, 0));
            self.result = null;
        }
        return self;
    }
}
