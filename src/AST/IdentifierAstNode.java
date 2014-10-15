package AST;

import IR.IrCode;
import IR.IrCodeState;
import SymbolScope.Symbol;

public class IdentifierAstNode extends AstNode {
    public String id;

    public IdentifierAstNode(String id) {
        this.id = id;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode c = new IrCode();
        Symbol<?> symbol = IrCodeState.CURRENT_SCOPE.findSymbol(id);
        c.type = symbol.type;
        c.result = symbol.name;
        return c;
    }
}