package AST;

import IR.*;
import SymbolScope.Symbol;
import SymbolScope.VariableType;

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
    public IrCode generateIrCode() {
        IrCode c = new IrCode();
        if(op == OpType.READ) {
            for(String id : ids) {
                Symbol<?> symbol = IrCodeState.CURRENT_SCOPE.findSymbol(id);
                if(symbol.type == VariableType.INT) {
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READI, id));
                }
                else if (symbol.type == VariableType.FLOAT){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READF, id));
                }
                else if (symbol.type == VariableType.STRING){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.READS, id));
                }
            }
        }
        else if(op == OpType.WRITE){
            for(String id : ids) {
                Symbol<?> symbol = IrCodeState.CURRENT_SCOPE.findSymbol(id);
                if(symbol.type == VariableType.INT) {
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITEI, id));
                }
                else if (symbol.type == VariableType.FLOAT){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITEF, id));
                }
                else if (symbol.type == VariableType.STRING){
                    c.irNodeList.add(new ITypeIrNode(IrNode.Opcode.WRITES, id));
                }
            }
        }
        return c;
    }
}