package AST;
import IR.IrCode;
import Nucleus.Literal;
import Nucleus.Operand;
import SymbolScope.ScopeNode;

public class LiteralAstNode extends AstNode {
    Object literal;
    Operand.DataType type;

    public LiteralAstNode(Operand.DataType t, Object l) {
        this.literal = l;
        this.type = t;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode c = new IrCode();
        c.result = new Literal(type, literal, Operand.OperandType.LITERAL);
        return c;
    }
}
