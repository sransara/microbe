package IR;

import Nucleus.Operand;
import Nucleus.Register;
import SymbolScope.FunctionScopeNode;
import SymbolScope.ScopeNode;

import java.util.LinkedList;
import java.util.List;

public class IrCode {
    public enum RLValue {
        RVALUE, LVALUE
    }

    public RLValue rlvalue = RLValue.LVALUE;
    public List<IrNode> irNodeList = new LinkedList<IrNode>();
    public Operand result;

    public void printIrCode() {
        for(IrNode n : irNodeList) {
            System.out.println(n.toString());
        }
    }

    public void printTinyCode() {
        for(IrNode n : irNodeList) {
            System.out.print(n.toTiny());
        }
    }
}