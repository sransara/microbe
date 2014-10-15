package AST;
import IR.IrCode;
import IR.IrCodeState;
import SymbolScope.Symbol;
import SymbolScope.VariableType;

public class LiteralAstNode extends AstNode {
    String literal;
    VariableType type;

    public LiteralAstNode(String l, VariableType v) {
        this.literal = l;
        this.type = v;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode c = new IrCode();
        c.type = type;
        c.result = literal;
        return c;
    }
}
