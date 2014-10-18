package IR;

import SymbolScope.VariableType;

import java.util.LinkedList;
import java.util.List;

public class IrCode {
    public enum RLValue {
        RVALUE, LVALUE
    }

    public RLValue rlvalue = RLValue.LVALUE;
    public List<IrNode> irNodeList = new LinkedList<IrNode>();
    public String result;
    public VariableType type = VariableType.VOID;

    public void PrintIrCode() {
        for(IrNode n : irNodeList) {
            System.out.println(n.toString());
        }
    }

    public void PrintAssembly() {
        for(IrNode n : irNodeList) {
            System.out.println(n.toAssembly());
        }
    }
    
    public boolean isTempResult() {
      return result.startsWith("$T");
    }
}
