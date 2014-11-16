package AST;

import IR.*;
import Nucleus.Operand;
import Nucleus.Symbol;
import SymbolScope.ScopeNode;

import java.util.List;

public class SystemOpAstNode extends AstNode {
    public static enum OpType {
        READ, WRITE
    }

    OpType op;
    List<String> ids;

    public SystemOpAstNode(OpType op, List<String> ids) {
        this.op = op;
        this.ids = ids;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode c = new IrCode();
        if(op == OpType.READ) {
            for(String id : ids) {
                Symbol symbol = scope.findSymbol(id);
                if(symbol.dataType == Operand.DataType.INT) {
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READI, symbol));
                }
                else if (symbol.dataType == Operand.DataType.FLOAT){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READF, symbol));
                }
                else if (symbol.dataType == Operand.DataType.STRING){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READS, symbol));
                }
            }
        }
        else if(op == OpType.WRITE){
            for(String id : ids) {
                Symbol symbol = scope.findSymbol(id);
                if(symbol.dataType == Operand.DataType.INT) {
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITEI, symbol));
                }
                else if (symbol.dataType == Operand.DataType.FLOAT){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITEF, symbol));
                }
                else if (symbol.dataType == Operand.DataType.STRING){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITES, symbol));
                }
            }
        }
        return c;
    }
}