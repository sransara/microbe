package IR;

import Nucleus.Operand;
import Nucleus.Register;
import SymbolScope.FunctionScopeNode;

import java.util.Collections;

import static java.lang.String.format;

public class ITypeIrNode extends IrNode{
    // System Interrupt
    Operand result;

    public ITypeIrNode(Opcode opcode, FunctionScopeNode scope, Operand op1) {
        super(opcode, scope);
        this.result = op1;
        if (opcode == Opcode.WRITEF || opcode == Opcode.WRITEI) {
            initGen(op1);
        }
        else if(opcode == Opcode.READI || opcode == Opcode.READF) {
            initKill(op1);
        }
    }

    @Override
    public String toString() {
        String a = null;
        switch(opcode) {
            case HALT: a = "; HALT"; break;
            default: a = "; " + opcode.name() + " " + result.reference;
        }
        return a;
    }

    @Override
    public String toTiny() {
        if (!isStarter()) {
            registers = prevs.get(0).registers;
        }
        String rop;
        String opRef = null;
        if (result != null) {
            opRef = result.reference;
        }

        String a = null;
        switch (opcode) {
            case WRITEF:
                rop = ensureRegister(result);
                dropDeadRegisters(null, rop);
                opRef = rop == null ? result.reference : rop;
                a = format("sys writer %s", opRef);
                break;
            case WRITEI:
                rop = ensureRegister(result);
                dropDeadRegisters(null, rop);
                opRef = rop == null ? result.reference : rop;
                a = format("sys writei %s", opRef);
                break;
            case WRITES:
                a = format("sys writes %s", opRef);
                break;
            case READF:
                rop = allocateRegister(result);
                dirtRegister(rop);
                a = format("sys readr %s", rop);
                break;
            case READI:
                rop = allocateRegister(result);
                dirtRegister(rop);
                a = format("sys readi %s", rop);
                break;
            case READS:
                a = format("sys reads %s", opRef);
                break;
            case HALT:
                a = format("sys halt");
                break;
        }
        tinyCode.append(a + "\n");

        if (isEnder()) {
            restoreRegisteredVariables();
        }

        return tinyCode.toString();
    }
}
