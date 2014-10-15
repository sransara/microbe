package IR;

import SymbolScope.SymbolScopeNode;

public class IrCodeState {
    public static SymbolScopeNode CURRENT_SCOPE;
    private static int TEMPX = -1;

    public static String newTemp() {
        return "$T" + ++TEMPX;
    }

    public static String getTemp() {
        return "$T" + TEMPX;
    }
}
