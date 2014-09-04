/**
 * Created by Sam Abeysiriwardane
 */
import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.util.HashMap;

public class Micro {
    public static final HashMap<Integer, String> TOKEN_NAMES = new HashMap<Integer, String>();
    static {
        TOKEN_NAMES.put(MicroScanner.KEYWORD, "KEYWORD");
        TOKEN_NAMES.put(MicroScanner.OPERATOR, "OPERATOR");
        TOKEN_NAMES.put(MicroScanner.IDENTIFIER, "IDENTIFIER");
        TOKEN_NAMES.put(MicroScanner.FLOATLITERAL, "FLOATLITERAL");
        TOKEN_NAMES.put(MicroScanner.INTLITERAL, "INTLITERAL");
        TOKEN_NAMES.put(MicroScanner.STRINGLITERAL, "STRINGLITERAL");
        TOKEN_NAMES.put(MicroScanner.COMMENT, "COMMENT");
        TOKEN_NAMES.put(MicroScanner.WHITES, "WHITES");
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("File argument was not provided.");
            return;
        }
        ScanSource(args[0]);
    }

    private static void ScanSource(String filename) {
        try {
            CharStream src = new ANTLRFileStream(filename);
            MicroScanner lexer = new MicroScanner(src);
            while(true) {
                Token token = lexer.nextToken();
                if(token.getType() == MicroScanner.EOF) {
                    break;
                }
                int ttype = token.getType();
                String tokenName = TOKEN_NAMES.get(ttype);
                if(ttype != MicroScanner.WHITES && ttype != MicroScanner.COMMENT) {
                    System.out.println("Token Type: " + tokenName);
                    System.out.println("Value: " + token.getText());
                }
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
