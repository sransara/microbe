package IR;

import Nucleus.Operand;

import java.util.LinkedList;
import java.util.List;

public class IrCode {
    public enum RLValue {
        RVALUE, LVALUE
    }

    public RLValue rlvalue = RLValue.LVALUE;
    public List<IrNode> irNodeList = new LinkedList<IrNode>();
    public Operand result;

    public void PrintIrCode() {
        for(IrNode n : irNodeList) {
            System.out.println(n.toString());
        }
    }

    public void PrintTiny() {
        for(IrNode n : irNodeList) {
            System.out.println(n.toTiny());
        }
    }
}