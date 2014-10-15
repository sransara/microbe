package AST;

import IR.IrCode;

public abstract class AstNode {
    public abstract IrCode generateIrCode();
}