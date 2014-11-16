package AST;

import IR.*;
import SymbolScope.ScopeNode;

public class WhileAstNode extends AstNode{
    String blockname;
    private String endBlockname;
    private String startBlockname;
    public AstNode condition;

    public WhileAstNode(String blockname, AstNode condition) {
        this.blockname = blockname;
        this.endBlockname = "END_" + blockname;
        this.startBlockname = "START_" + blockname;
        this.condition = condition;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode self = new IrCode();
        /* ---- Need to generate this
        START_<blockname>:
            <bool expr>, <blockname>
            j<!op> END_<blockname>
        <blockname>
            <stmt_list>
            jmp START_<blockname>
        END_<blockname>:
        ------ alright then let's do it --- */

        // START_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.startBlockname));
        //  <code for bool_expr_1>
        ((ConditionOpAstNode)condition).jumpLabel = this.blockname;
        self.irNodeList.addAll(condition.generateIrCode(scope).irNodeList);
        // j<!op> ELSE_1
        self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.endBlockname));
        // <blockname> added with scope gerenated IR
        // <code for stmt_list_1>
        ScopeNode cscope = scope.children.get(this.blockname);
        cscope.generateIrCode();
        self.irNodeList.addAll(cscope.irCode.irNodeList);

        // jmp START_<blockname>
        self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.startBlockname));
        // END_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.endBlockname));
        return self;
    }
}
