package AST;

import IR.*;
import SymbolScope.ScopeNode;

public class IfAstNode extends AstNode{
    String blockname;
    private String endBlockname;
    private String startBlockname;
    public AstNode condition;
    public IfAstNode otherwise;

    public IfAstNode(String blockname, AstNode condition, AstNode otherwise) {
        this.blockname = blockname;
        this.endBlockname = "END_" + blockname;
        this.startBlockname = "START_" + blockname;
        this.condition = condition;
        this.otherwise = (IfAstNode) otherwise;
    }

    @Override
    public IrCode generateIrCode(ScopeNode scope) {
        IrCode self = new IrCode();
        /* ---- Need to generate this
        START_<blockname>:
            <code for bool_expr_1>
            j<!op> ELSE_1
        <blockname>:
            <code for stmt_list_1>
            jmp END_<blockname>
        <otherwise_blockname>:
            <code for stmt_list_2>
        END_<blockname>:
        ------ alright then let's do it --- */

        // START_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.startBlockname));
        //  <code for bool_expr_1>
        if(condition != null) {
            ((ConditionOpAstNode)condition).jumpLabel = this.blockname;
            self.irNodeList.addAll(condition.generateIrCode(scope).irNodeList);
        }
        // j<!op> ELSE_1
        if(otherwise != null) {
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, otherwise.blockname));
        }
        else if (condition != null){
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.endBlockname));
        }
        // <blockname> added with scope generated IR
        // <code for stmt_list_1>
        ScopeNode cscope = scope.children.get(this.blockname);
        cscope.generateIrCode();
        self.irNodeList.addAll(cscope.irCode.irNodeList);

        // <otherwise>
        if(otherwise != null) {
            // jmp END_<blockname>
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.endBlockname));

            // otherwise is also another IfAstNode with some elements set as NULL
            // In otherwsie: condition, otherwise is NULL
            // then is not NULL
            self.irNodeList.addAll(otherwise.generateIrCode(scope).irNodeList);
        }
        // END_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.endBlockname));
        return self;
    }
}
