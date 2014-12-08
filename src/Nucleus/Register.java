package Nucleus;

public class Register {
    public static final int REG_N = 4;
    public Operand operand = null;
    public boolean isDirty = false;

    public Register(Operand operand) {
        this.operand = operand;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        else {
            return ((Register) o).operand.equals(operand);
        }
    }

    public static String getReference(int n) {
        return "r" + n;
    }

    public static int getDereference(String regRef) {
        return Integer.parseInt(regRef.replace("r", ""));
    }

    @Override
    public String toString() {
        if(isDirty) {
            return operand.reference + "*";
        }
        else {
            return operand.reference;
        }
    }
}