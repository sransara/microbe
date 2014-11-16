package AST;

import IR.IrCode;
import SymbolScope.ScopeNode;

public abstract class AstNode {
    public abstract IrCode generateIrCode(ScopeNode scope);
}