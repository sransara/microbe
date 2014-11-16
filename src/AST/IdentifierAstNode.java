package AST;

import IR.IrCode;
import SymbolScope.ScopeNode;

public class IdentifierAstNode extends AstNode {
    public String id;

    public IdentifierAstNode(String id) {
        this.id = id;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode c = new IrCode();
        c.result = scope.findSymbol(id);
        return c;
    }
}