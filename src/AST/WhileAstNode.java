package AST;

import IR.IrCode;
import IR.IrNode;
import IR.JTypeIrNode;
import IR.LTypeIrNode;

import java.util.List;

public class WhileAstNode extends AstNode{
    String blockname;
    private String end_blockname;
    private String start_blockname;
    public AstNode condition;
    public List<AstNode> then;

    public WhileAstNode(String blockname, AstNode condition, List<AstNode> then) {
        this.blockname = blockname;
        this.end_blockname = "END_" + blockname;
        this.start_blockname = "START_" + blockname;
        this.condition = condition;
        this.then = then;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode self = new IrCode();
        /* ---- Need to generate this
       <blockname>:
            <bool expr>, START_<blockname>
            j<!op> END_<blockname>
       START_<blockname>
            <stmt_list>
            jmp <blockname>
        END_<blockname>:
        ------ alright then let's do it --- */

        // <blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.blockname));
        //  <code for bool_expr_1>
        ((ConditionOpAstNode)condition).jump_label = this.start_blockname;
        self.irNodeList.addAll(condition.generateIrCode().irNodeList);
        // j<!op> ELSE_1
        self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.end_blockname));
        // START_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.start_blockname));
        // <code for stmt_list_1>
        for(AstNode n : then) {
            self.irNodeList.addAll(n.generateIrCode().irNodeList);
        }
        // jmp <blockname>
        self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.blockname));
        // END_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.end_blockname));
        return self;
    }
}
