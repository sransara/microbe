package IR;

import Nucleus.Operand;
import Nucleus.Register;
import SymbolScope.FunctionScopeNode;
import SymbolScope.ScopeNode;

import java.lang.reflect.Array;
import java.util.*;


public abstract class IrNode {
    public static enum Opcode {
        ADDI, SUBI, MULTI, DIVI,
        ADDF, SUBF, MULTF, DIVF,
        STOREI, STOREF,
        GT, GE, LT, LE, NE, EQ,
        JUMP,
        LABEL,
        READI, READF, READS, WRITEI, WRITEF, WRITES,
        JSR, PUSH, POP, RET, LINK,
        HALT, UNKNWN
    }

    public Opcode opcode;
    public FunctionScopeNode functionScope;

    // specifically used to build Flow Graph
    public List<IrNode> nexts = new ArrayList<IrNode>();
    public List<IrNode> prevs = new ArrayList<IrNode>();
    public boolean starter = false;
    public boolean ender = false;
    public boolean isStarter() {
        if(starter) {
            tinyCode.append("; DBG STARTING BB\n");
        }
        tinyCode.append("; -- " + this + "\n; DBG liveout "+ Arrays.toString(liveOut.toArray()) + "\n");
        return starter;
    }
    public boolean isEnder() {
        tinyCode.append("; DBG END REGISTERS " + Arrays.toString(registers.toArray()) + "\n");
        if(ender) {
            tinyCode.append("; DBG ENDING BB\n");
        }
        return ender;
    }
    public boolean isJsr() {
        return opcode == Opcode.JSR;
    }
    public boolean isJmp() {
        return opcode == Opcode.JUMP;
    }
    public boolean isReturn() {
        return opcode == Opcode.RET;
    }
    public boolean isLabel() {
        return opcode == Opcode.LABEL;
    }
    public boolean usesLabel() {
        return opcode == Opcode.JUMP
                // || opcode == Opcode.JSR : Function calls should be treated as straight-line IR nodes
                || opcode == Opcode.GT
                || opcode == Opcode.GE
                || opcode == Opcode.LT
                || opcode == Opcode.LE
                || opcode == Opcode.NE
                || opcode == Opcode.EQ;
    }
    public String label = null;
    // end used to build control flow graph

    // used in liveness analysis
    public Set<Operand> gen = new LinkedHashSet<Operand>();
    public Set<Operand> kill = new LinkedHashSet<Operand>();
    public Set<Operand> liveIn = new LinkedHashSet<Operand>();
    public Set<Operand> liveOut = new LinkedHashSet<Operand>();

    protected void initGen(Operand...operands) {
        for(Operand o : operands) {
            if(o != null && o.isRegisterable()) {
                gen.add(o);
            }
        }
    }

    protected void initKill(Operand...operands) {
        for(Operand o : operands) {
            if(o != null && o.isRegisterable()) {
                kill.add(o);
            }
        }
    }
    // end used in liveness analysis

    public IrNode(Opcode opcode, FunctionScopeNode scope) {
        this.opcode = opcode;
        this.functionScope = scope;
    }

    public abstract String toString();

    // Everything next is used for tiny generation

    // registers
    public List<Register> registers = Arrays.asList(new Register[Register.REG_N]);

    // tiny code
    protected StringBuilder tinyCode = new StringBuilder();

    public abstract String toTiny();

    protected String operandToTiny(Operand operand) {
        String c = operand.reference;
        if(c.startsWith("$P")) {
            int p_offset = Integer.parseInt(c.replace("$P", "")) + Register.REG_N + 1; // offset by 1 for return address
            c = "$" + p_offset;
        }
        else if(c.startsWith("$R")) {
            int r_offset = Integer.parseInt(c.replace("$R", "")) + Register.REG_N + 1; // offset by 1 for return address
            c = "$" + r_offset;
        }
        else if(c.startsWith("$L")) {
            int l_offset = Integer.parseInt(c.replace("$L", ""));
            c = "$-" + l_offset;
        }
        return c;
    }

    protected String ensureRegister(Operand operand, Operand...others) {
        // Others is the list of operands that might be used with this operand
        if(operand == null || !operand.isRegisterable()) {
            return null;
        }
        int r = registers.indexOf(new Register(operand));
        if(r > -1) {
            tinyCode.append("; DBG ENSURE "+ operand.reference +" -> "+ Register.getReference(r) +"\n");
            return Register.getReference(r);
        }
        else {
            String newr = allocateRegister(operand, others);
            tinyCode.append("; DBG ALLOC ENSURE "+ operand.reference + " -> "+ newr +"\n");
            tinyCode.append("move " + operandToTiny(operand) + " " + newr + "\n");
            return newr;
        }
    }

    protected String allocateRegister(Operand operand, Operand...others) {
        int ri = 0;
        int nullri = registers.indexOf(null);
        int opri = registers.indexOf(new Register(operand));
        String regRef = null;
        if(opri > -1) {
            ri = opri;
            regRef = Register.getReference(ri);
        }
        else if(nullri > -1) {
            ri = nullri;
            regRef = Register.getReference(ri);
        }
        else {
            // the lower indexed liveOut elements are more likely to be used recently
            Operand[] liveOut_a = liveOut.toArray(new Operand[liveOut.size()]);
            int max_j = -1;
            int max_j_i = -1;
            for(int i = 0; i < Register.REG_N; i++) {
                Register r = registers.get(i);
                if(Arrays.asList(others).contains(r.operand)) {
                    continue; // others.length must be less than REG_N
                }
                if(!liveOut.contains(r.operand)) {
                    max_j_i = i;
                    break;
                }
                for(int j = 0; j < liveOut_a.length; j++) {
                    // as we didn't hit the `break` one of these must be true
                    if (r.operand.equals(liveOut_a[j])) {
                        if(max_j < j) {
                            max_j = j;
                            max_j_i = i;
                        }
                    }
                }
            }
            ri = max_j_i;
            regRef = Register.getReference(max_j_i);
            tinyCode.append("; DBG KICK OUT " + registers.get(ri).operand + " @ " + regRef);
            tinyCode.append(" others:" + Arrays.toString(others) + "\n");
            freeRegister(regRef);
        }
        tinyCode.append("; DBG ALLOCATE "+ operand.reference +" -> "+ regRef +"\n");
        registers.set(ri, new Register(operand));
        return regRef;
    }

    protected void freeRegister(String regRef) {
        int ri = Register.getDereference(regRef);
        Register r = registers.get(ri);
        if(r.isDirty && liveOut.contains(r.operand)) {
            if(r.operand.operandType == Operand.OperandType.TEMPORARY) {
                String ref =  String.format("$L%d", functionScope.local_x);
                tinyCode.append("; DBG SPILLING TEMP " + r.operand + " " + ref + "\n");
                r.operand.reference = ref;
                r.operand.operandType = Operand.OperandType.SPILLED_TEMPORARY;
                functionScope.local_x++;
            }
            tinyCode.append("move " + regRef + " " + operandToTiny(r.operand) + " ; DBG FREE STORE \n");
        }
        else {
            tinyCode.append("; DBG DROP "+ r.operand.reference +" @ "+ regRef +"\n");
        }
        registers.set(ri, null);
    }

    // Set dirty bit
    protected void dirtRegister(String regRef) {
        Register r = registers.get(Register.getDereference(regRef));
        r.isDirty = true;
    }

    // Not live anymore
    protected void dropDeadRegisters(String... regRefs) {
        for(String regRef : regRefs) {
            if(regRef == null) { continue; }
            Register r = registers.get(Register.getDereference(regRef));
            if(r != null && !liveOut.contains(r.operand)) {
                freeRegister(regRef);
            }
        }
    }

    protected void restoreRegisteredVariables() {
        for(int i = 0; i < Register.REG_N; i++) {
            String regRef = Register.getReference(i);
            Register r = registers.get(i);
            if (r != null && r.operand != null) {
                freeRegister(regRef);
            }
        }
    }

    protected void restoreRegisteredGlobalVariables() {
        for(int i = 0; i < Register.REG_N; i++) {
            String regRef = Register.getReference(i);
            Register r = registers.get(i);
            if(r == null || r.operand == null) { continue; }
            else if (r.operand.operandType == Operand.OperandType.GLOBAL_SYMBOL && r.isDirty) {
                tinyCode.append("move " + regRef + " " + operandToTiny(r.operand) + " ; DBG GLOBAL RESTORE \n");
                r.isDirty = false;
            }
        }
    }
}
