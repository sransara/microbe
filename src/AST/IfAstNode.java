package AST;

import IR.*;

import java.util.List;

public class IfAstNode extends AstNode{
    String blockname;
    private String end_blockname;
    private String start_blockname;
    public AstNode condition;
    public List<AstNode> then;
    public IfAstNode otherwise;

    public IfAstNode(String blockname, AstNode condition, List<AstNode> then, AstNode otherwise) {
        this.blockname = blockname;
        this.end_blockname = "END_" + blockname;
        this.start_blockname = "START_" + blockname;
        this.condition = condition;
        this.then = then;
        this.otherwise = (IfAstNode) otherwise;
    }

    @Override
    public IrCode generateIrCode() {
        IrCode self = new IrCode();
        /* ---- Need to generate this
        <blockname>:
            <code for bool_expr_1>
            j<!op> ELSE_1
        START_<blockname>:
            <code for stmt_list_1>
            jmp END_<blockname>
        <otherwise_blockname>:
            <code for stmt_list_2>
        END_<blockname>:
        ------ alright then let's do it --- */

        // <blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.blockname));
        //  <code for bool_expr_1>
        if(condition != null) {
            ((ConditionOpAstNode)condition).jump_label = this.start_blockname;
            self.irNodeList.addAll(condition.generateIrCode().irNodeList);
        }
        // j<!op> ELSE_1
        if(otherwise != null) {
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, otherwise.blockname));
        }
        else if (condition != null){
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.end_blockname));
        }
        // START_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.start_blockname));
        // <code for stmt_list_1>
        for(AstNode n : then) {
            self.irNodeList.addAll(n.generateIrCode().irNodeList);
        }

        // <otherwise>
        if(otherwise != null) {
            // jmp END_<blockname>
            self.irNodeList.add(new JTypeIrNode(IrNode.Opcode.JUMP, this.end_blockname));

            // otherwise is also another IfAstNode with some elements set as NULL
            // In otherwsie: condition, otherwise is NULL
            // then is not NULL
            self.irNodeList.addAll(otherwise.generateIrCode().irNodeList);
        }
        // END_<blockname>
        self.irNodeList.add(new LTypeIrNode(IrNode.Opcode.LABEL, this.end_blockname));
        return self;
    }
}
